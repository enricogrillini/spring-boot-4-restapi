package it.eg.cookbook.service;

import it.eg.cookbook.model.Documento;
import it.eg.cookbook.model.mapper.DocumentoMapper;
import it.eg.cookbook.model.pojo.AutorePojo;
import it.eg.cookbook.model.pojo.DocumentoPojo;
import it.eg.cookbook.repository.AutoreDao;
import it.eg.cookbook.repository.DocumentoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentoService {

    private final AutoreDao autoreDao;
    private final DocumentoDao documentoDao;

    private final DocumentoMapper documentoMapper;

    public ResponseEntity<Documento> get(Long idDocumento) {
        DocumentoPojo documentoPojo = documentoDao.findByIdOrThrow(idDocumento);
        List<AutorePojo> autorePojoList = autoreDao.findAll(idDocumento);

        return ResponseEntity.ok(documentoMapper.pojoToApi(documentoPojo, autorePojoList));
    }
//
//        @Transactional
//    public Documento create(Documento documento) {
//        DocumentoEntity documentoEntity = documentoMapper.apiToEntity(documento);
//
//        // Aggiungo gli autori
//        for (Autore autore : documento.getAutori()) {
//            DocumentoAutoreEntity documentoAutoreEntity = new DocumentoAutoreEntity();
//            documentoAutoreEntity.setDocumento(documentoEntity);
//            documentoAutoreEntity.setAutore(autoreRepository.findByNomeAndCognomeOrThrow(autore.getNome(), autore.getCognome()));
//
//            documentoEntity.getDocumentoAutoreList().add(documentoAutoreEntity);
//        }
//
//        documentoRepository.save(documentoEntity);
//
//        return documentoMapper.entityToApi(documentoEntity);
//    }
//
//    @Transactional
//    public Documento update(Documento documento) {
//        DocumentoEntity documentoEntity = documentoRepository.findByIdOrThrow(documento.getId());
//        documentoMapper.updateEntity(documentoEntity, documento);
//
//        // Elimino gli autori attulamente presenti
//        documentoEntity.getDocumentoAutoreList().clear();
//        documentoRepository.saveAndFlush(documentoEntity);
//
//        // Aggiungo gli autori nuovi
//        for (Autore autore : documento.getAutori()) {
//            DocumentoAutoreEntity documentoAutoreEntity = new DocumentoAutoreEntity();
//            documentoAutoreEntity.setDocumento(documentoEntity);
//            documentoAutoreEntity.setAutore(autoreRepository.findByNomeAndCognomeOrThrow(autore.getNome(), autore.getCognome()));
//
//            documentoEntity.getDocumentoAutoreList().add(documentoAutoreEntity);
//        }
//        documentoRepository.saveAndFlush(documentoEntity);
//
//        return documentoMapper.entityToApi(documentoEntity);
//    }
//
//    @Transactional
//    public Message delete(Long id) {
//        DocumentoEntity documentoEntity = documentoRepository.findByIdOrThrow(id);
//        documentoRepository.delete(documentoEntity);
//
//        return ResponseCode.OK.getMessage("Documento eliminato correttamente");
//    }
}
