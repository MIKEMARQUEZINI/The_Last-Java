package prosper.thelast.IntegrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import prosper.thelast.DTO.Orders.OrdersDTO;
import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.repository.OrdersRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = MongoDBSettings.class)
public class ITOrdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private OrdersDTO ordersDTO;
    private OrdersDTO ordersDTOReturns;
    private String ordersJson;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        ordersRepository.deleteAll();
        createOrdersForTest();
    }

    public void createProductAndValidationIT() throws Exception {
        createOrdersBaseIT();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orders/search/{productName}",ordersDTO.getProductName()))
                .andExpect(status().isOk())
                .andReturn();
        String resultProduct = result.getResponse().getContentAsString();
        assert resultProduct.contains(ordersDTO.getProductName());

    }
    @Test
    public void registerOrdersNullIT() throws Exception {
        createOrdersDTONameNull();
        ordersJson = objectMapper.writeValueAsString(ordersDTO);
        createOrdersNaBaseNullError();

    }
    @Test
    public void searchOrdersByNameNotFoundIT() throws Exception {
        createOrders();
        ordersDTO.setProductName("Different Product Name!");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orders/search/{productName}",
                                ordersDTO.getProductName()))
                .andExpect(status().isNotFound())
                .andReturn();
        String ret = result.getResponse().getContentAsString();
        assert ret.isEmpty();

    }
    @Test
    public void deleteOneOrdersIT() throws Exception {
        createOrders();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/orders/{id}", ordersDTOReturns.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String ret = result.getResponse().getContentAsString();
        assert ret.contains("Successfully deleted");

    }

    private void createOrders() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordersJson))
                .andExpect(status().isOk())
                .andReturn();
        ordersDTOReturns = objectMapper.readValue(result.getResponse().getContentAsString(), OrdersDTO.class);

    }
    private void createOrdersNaBaseNullError() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordersJson))
                .andExpect(status().isBadRequest())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody, containsString("the quantity must be greater than 0"));
        assertThat(responseBody, containsString("Enter the orders for product name"));

    }

    private void createOrdersDTONameNull() {
        ordersDTO = new OrdersDTO();
        ordersDTO.setPointSales("Orders Point Sales");
        ordersDTO.setQuantity(1);
    }

    private void createOrdersForTest() throws JsonProcessingException {
        createOrdersDTO();
        ordersJson = objectMapper.writeValueAsString(ordersDTO);
    }

    private void createOrdersDTO() {
        ordersDTO = new OrdersDTO();
        ordersDTO.setProductName("Orders Testing IT");
        ordersDTO.setQuantity(10);
        ordersDTO.setPointSales("in the palm of your hand");
    }

    private void createOrdersBaseIT() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordersJson))
                .andExpect(status().isOk())
                .andReturn();
        ordersDTOReturns = objectMapper.readValue(result.getResponse().getContentAsString(),OrdersDTO.class);
    }

}
