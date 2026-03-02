package it.eg.cookbook.controller;

import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.pojo.DocumentoPojo;
import it.eg.cookbook.model.Message;
import it.eg.cookbook.model.entity.DocumentoEntity;
import it.eg.cookbook.model.mapper.DocumentoMapper;
import it.eg.cookbook.repository.AutoreDao;
import it.eg.cookbook.repository.DocumentoRepository;
import it.eg.cookbook.repository.OldDocumentoRepository;
import it.eg.cookbook.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoController implements DocumentoApi {

    private final DocumentoMapper documentoMapper;
    private final OldDocumentoRepository oldDocumentoRepository;
    private final DocumentoRepository documentoRepository;
    private final DocumentoService documentoService;
    private final AutoreDao autoreDao;

    @Override
    public ResponseEntity<Documento> create(Documento documento) {
        if (documento.getId() != null) {
            throw new ApiException(ResponseCode.BUSINESS_ERROR, "L'id documento non deve essere indicato");
        }

        return ResponseEntity.ok(documentoService.create(documento));
    }

    @Override
    public ResponseEntity<Documento> update(Long id, Documento documento) {
        if (!id.equals(documento.getId())) {
            throw new ApiException(ResponseCode.BUSINESS_ERROR, "Id documento incoerente");
        }

        return ResponseEntity.ok(documentoService.update(documento));
    }

    @Override
    public ResponseEntity<Message> delete(Long id) {
        return ResponseEntity.ok(documentoService.delete(id));
    }

    @Override
    public ResponseEntity<List<Documento>> find() {

        List<DocumentoPojo>  lista = documentoRepository.findAll();
        autoreDao.findAll(1L);
        autoreDao.update();

        Iterable<DocumentoEntity> list = oldDocumentoRepository.findAll();

        return ResponseEntity.ok(documentoMapper.documentoAutorelistEntityToApi(list));
    }

    @Override
    public ResponseEntity<Documento> get(Long id) {
        DocumentoEntity documentoEntity = oldDocumentoRepository.findByIdOrThrow(id);

        return ResponseEntity.ok(documentoMapper.entityToApi(documentoEntity));
    }

}

