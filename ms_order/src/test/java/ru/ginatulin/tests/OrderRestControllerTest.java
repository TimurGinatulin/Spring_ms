package ru.ginatulin.tests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.RestTemplate;
//import ru.ginatulin.controller.v1.OrderRestController;
//import ru.ginatulin.dto.AuthRequestDto;
//import ru.ginatulin.dto.AuthResponseDto;
//import ru.ginatulin.models.dto.OrderCartDto;
//import ru.ginatulin.models.dto.OrderItemCartDto;
//
//import java.util.Collections;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class OrderRestControllerTest {
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private OrderRestController controller;
//
//    @Test
//    public void getAllOrderTest() throws Exception {
//        mvc.perform(get("/orders")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].idUser", is(1)));
//    }
//
//    @Test
//    public void getAllOrderTestAsList() throws Exception {
//        mvc.perform(get("/orders/list")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("ids", "1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].idUser", is(1)));
//    }
//
//    @Test
//    public void postTest() throws Exception {
//        OrderCartDto cartDto = new OrderCartDto(1l, Collections.singletonList(new OrderItemCartDto(1l,1,1d)));
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String result = ow.writeValueAsString(cartDto);
//
//        AuthRequestDto authRequestDto = new AuthRequestDto();
//        authRequestDto.setEmail("ginatulin.tr@mail.com");
//        authRequestDto.setPassword("100");
//
//        RestTemplate template = new RestTemplate();
//        String url = "http://127.0.0.1:5555/api/v1/auth/login";
//        AuthResponseDto token = template.postForObject(url, authRequestDto, AuthResponseDto.class);
//
//        mvc.perform(post("/orders")
//                        .content(result)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", token.getToken()))
//                .andExpect(status().isCreated());
//    }
}
