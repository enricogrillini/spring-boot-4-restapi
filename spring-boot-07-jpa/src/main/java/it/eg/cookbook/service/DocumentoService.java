package it.eg.cookbook.service;

import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.model.Autore;
import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.DocumentoPage;
import it.eg.cookbook.model.Message;
import it.eg.cookbook.model.entity.DocumentoAutoreEntity;
import it.eg.cookbook.model.entity.DocumentoEntity;
import it.eg.cookbook.model.mapper.DocumentoMapper;
import it.eg.cookbook.repository.AutoreRepository;
import it.eg.cookbook.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoService {

    private final DocumentoMapper documentoMapper;
    private final DocumentoRepository documentoRepository;
    private final AutoreRepository autoreRepository;

    public ResponseEntity<DocumentoPage> find(Integer pageNumber, Integer pageSize, String nome, String descrizione, LocalDate dataDa, LocalDate dataA) {
        Page<DocumentoEntity> page = documentoRepository.find(PageRequest.of(pageNumber, pageSize), nome, descrizione, dataDa, dataA);

        return ResponseEntity.ok(documentoMapper.pageEntityToApi(page));
    }

    @Transactional
    public Documento create(Documento documento) {
        DocumentoEntity documentoEntity = documentoMapper.apiToEntity(documento);

        // Aggiungo gli autori
        for (Autore autore : documento.getAutori()) {
            DocumentoAutoreEntity documentoAutoreEntity = new DocumentoAutoreEntity();
            documentoAutoreEntity.setDocumento(documentoEntity);
            documentoAutoreEntity.setAutore(autoreRepository.findByNomeAndCognomeOrThrow(autore.getNome(), autore.getCognome()));

            documentoEntity.getDocumentoAutoreList().add(documentoAutoreEntity);
        }

        documentoRepository.save(documentoEntity);

        return documentoMapper.entityToApi(documentoEntity);
    }

    @Transactional
    public Documento update(Documento documento) {
        DocumentoEntity documentoEntity = documentoRepository.findByIdOrThrow(documento.getId());
        documentoMapper.updateEntity(documentoEntity, documento);

        // Elimino gli autori attulamente presenti
        documentoEntity.getDocumentoAutoreList().clear();
        documentoRepository.saveAndFlush(documentoEntity);

        // Aggiungo gli autori nuovi
        for (Autore autore : documento.getAutori()) {
            DocumentoAutoreEntity documentoAutoreEntity = new DocumentoAutoreEntity();
            documentoAutoreEntity.setDocumento(documentoEntity);
            documentoAutoreEntity.setAutore(autoreRepository.findByNomeAndCognomeOrThrow(autore.getNome(), autore.getCognome()));

            documentoEntity.getDocumentoAutoreList().add(documentoAutoreEntity);
        }
        documentoRepository.saveAndFlush(documentoEntity);

        return documentoMapper.entityToApi(documentoEntity);
    }

    @Transactional
    public Message delete(Long id) {
        DocumentoEntity documentoEntity = documentoRepository.findByIdOrThrow(id);
        documentoRepository.delete(documentoEntity);

        return ResponseCode.OK.getMessage("Documento eliminato correttamente");
    }
}
