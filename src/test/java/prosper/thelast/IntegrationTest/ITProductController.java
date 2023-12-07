package prosper.thelast.IntegrationTest;

import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.repository.ProductRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = MongoDBSettings.class)

public class ITProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private ProductDTO productDTO;
    private ProductDTO productDTOReturns;
    private String productJson;
    
    @BeforeEach
    public void setUp() throws JsonProcessingException {
        productRepository.deleteAll();
        createProductForTest();
    }

    @Test
    public void createProductAndValidationIT() throws Exception {
        createProductBaseIT();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/search/{name}",productDTO.getName()))
                .andExpect(status().isOk())
                .andReturn();
        String resultProduct = result.getResponse().getContentAsString();
        assert resultProduct.contains(productDTO.getName());

    }

    private void createProductForTest() throws JsonProcessingException {
        createProductDTO();
        productJson = objectMapper.writeValueAsString(productDTO);
    }

    private void createProductDTO() {
        productDTO = new ProductDTO();
        productDTO.setName("Product Test IT");
        productDTO.setPrice(10.00F);
        productDTO.setDescriptions("in the palm of your hand");
    }



    private void createProductBaseIT() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andReturn();
        productDTOReturns = objectMapper.readValue(result.getResponse().getContentAsString(),ProductDTO.class);
    }

}
