package it.eg.cookbook.controller;

import it.eg.cookbook.error.ApiException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.DocumentoPage;
import it.eg.cookbook.model.Message;
import it.eg.cookbook.model.entity.DocumentoEntity;
import it.eg.cookbook.model.mapper.DocumentoMapper;
import it.eg.cookbook.repository.DocumentoRepository;
import it.eg.cookbook.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoController implements DocumentoApi {

    private final DocumentoMapper documentoMapper;
    private final DocumentoRepository documentoRepository;
    private final DocumentoService documentoService;

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
    public ResponseEntity<DocumentoPage> find(Integer pageNumber, Integer pageSize, String nome, String descrizione, LocalDate dataDa, LocalDate dataA) {
        return documentoService.find(pageNumber, pageSize, nome, descrizione, dataDa, dataA);
    }


    @Override
    public ResponseEntity<Documento> get(Long id) {
        DocumentoEntity documentoEntity = documentoRepository.findByIdOrThrow(id);

        return ResponseEntity.ok(documentoMapper.entityToApi(documentoEntity));
    }

}

