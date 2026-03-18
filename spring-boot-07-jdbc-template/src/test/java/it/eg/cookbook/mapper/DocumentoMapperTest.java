package it.eg.cookbook.mapper;

import it.eg.cookbook.model.mapper.DocumentoMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import tools.jackson.databind.json.JsonMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DocumentoMapperTest {

    @Autowired
    protected DocumentoMapper jobEventMapper;

    @Autowired
    JsonMapper jsonMapper;



//    @TestFactory
//    @DisplayName("Check mapping Api to Entity")
//    Stream<DynamicTest> mapping_Api_to_Entity() {
//        File inputDir = new File("./src/test/resources/json/DocumentoMapperTest/api");
//
//        return Arrays.stream(inputDir.listFiles())
//                .filter(File::isFile)
//                .map(dt -> DynamicTest.dynamicTest("Mapping: " + dt.getName(), () -> {
//                    Documento documento = TestUtil.readObject("DocumentoMapperTest/entity/" + dt.getName(), Documento.class);
//                    DocumentoEntity documentoEntity = jobEventMapper.apiToEntity(documento);
//
//                    TestUtil.assertJsonEqualsFile("DocumentoMapperTest/entity/" + dt.getName(), jsonMapper.writeValueAsString(documentoEntity));
//                }));
//    }
//
//    @TestFactory
//    Stream<DynamicTest> mapping_Entity_to_Api() {
//        File inputDir = new File("./src/test/resources/json/DocumentoMapperTest/entity");
//
//        return Arrays.stream(inputDir.listFiles())
//                .filter(File::isFile)
//                .map(dt -> DynamicTest.dynamicTest("Mapping: " + dt.getName(), () -> {
//                    Documento documento = TestUtil.readObject("DocumentoMapperTest/api/" + dt.getName(), Documento.class);
//                    DocumentoEntity documentoEntity = jobEventMapper.apiToEntity(documento);
//
//                    TestUtil.assertJsonEqualsFile("DocumentoMapperTest/api/" + dt.getName(), jsonMapper.writeValueAsString(documentoEntity));
//                }));
//    }

}
