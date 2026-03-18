package it.eg.cookbook.controller;

import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.Message;
import it.eg.cookbook.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoController implements DocumentoApi {

    private final DocumentoService documentoService;

    @Override
    public ResponseEntity<Documento> create(Documento documento) {
//        if (documento.getId() != null) {
//            throw new ApiException(ResponseCode.BUSINESS_ERROR, "L'id documento non deve essere indicato");
//        }
//
//        return ResponseEntity.ok(documentoService.create(documento));
        return null;
    }

    @Override
    public ResponseEntity<Documento> update(Long id, Documento documento) {
//        if (!id.equals(documento.getId())) {
//            throw new ApiException(ResponseCode.BUSINESS_ERROR, "Id documento incoerente");
//        }
//
//        return ResponseEntity.ok(documentoService.update(documento));
        return null;
    }

    @Override
    public ResponseEntity<Message> delete(Long id) {
        // return ResponseEntity.ok(documentoService.delete(id));
        return null;
    }

    @Override
    public ResponseEntity<List<Documento>> find() {

//        List<DocumentoPojo>  lista = documentoRepository.findAll();
////        autoreDao.findAll(1L);
////        autoreDao.update();
////
////        Iterable<DocumentoEntity> list = oldDocumentoRepository.findAll();
////
////        return ResponseEntity.ok(documentoMapper.documentoAutorelistEntityToApi(list));

        return null;
    }

    @Override
    public ResponseEntity<Documento> get(Long id) {
        return documentoService.get(id);
    }

}

