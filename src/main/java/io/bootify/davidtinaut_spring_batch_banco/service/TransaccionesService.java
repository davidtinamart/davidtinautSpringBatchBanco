package io.bootify.davidtinaut_spring_batch_banco.service;

import io.bootify.davidtinaut_spring_batch_banco.domain.Transacciones;
import io.bootify.davidtinaut_spring_batch_banco.model.TransaccionesDTO;
import io.bootify.davidtinaut_spring_batch_banco.repos.TransaccionesRepository;
import io.bootify.davidtinaut_spring_batch_banco.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TransaccionesService {

    private final TransaccionesRepository transaccionesRepository;

    public TransaccionesService(final TransaccionesRepository transaccionesRepository) {
        this.transaccionesRepository = transaccionesRepository;
    }

    public List<TransaccionesDTO> findAll() {
        final List<Transacciones> transaccioneses = transaccionesRepository.findAll(Sort.by("id"));
        return transaccioneses.stream()
                .map(transacciones -> mapToDTO(transacciones, new TransaccionesDTO()))
                .toList();
    }

    public TransaccionesDTO get(final Long id) {
        return transaccionesRepository.findById(id)
                .map(transacciones -> mapToDTO(transacciones, new TransaccionesDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TransaccionesDTO transaccionesDTO) {
        final Transacciones transacciones = new Transacciones();
        mapToEntity(transaccionesDTO, transacciones);
        return transaccionesRepository.save(transacciones).getId();
    }

    public void update(final Long id, final TransaccionesDTO transaccionesDTO) {
        final Transacciones transacciones = transaccionesRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transaccionesDTO, transacciones);
        transaccionesRepository.save(transacciones);
    }

    public void delete(final Long id) {
        transaccionesRepository.deleteById(id);
    }

    private TransaccionesDTO mapToDTO(final Transacciones transacciones,
            final TransaccionesDTO transaccionesDTO) {
        transaccionesDTO.setId(transacciones.getId());
        transaccionesDTO.setFecha(transacciones.getFecha());
        transaccionesDTO.setCantidad(transacciones.getCantidad());
        transaccionesDTO.setTipotrans(transacciones.getTipotrans());
        transaccionesDTO.setCuentaorigen(transacciones.getCuentaorigen());
        transaccionesDTO.setCuentadestino(transacciones.getCuentadestino());
        return transaccionesDTO;
    }

    private Transacciones mapToEntity(final TransaccionesDTO transaccionesDTO,
            final Transacciones transacciones) {
        transacciones.setFecha(transaccionesDTO.getFecha());
        transacciones.setCantidad(transaccionesDTO.getCantidad());
        transacciones.setTipotrans(transaccionesDTO.getTipotrans());
        transacciones.setCuentaorigen(transaccionesDTO.getCuentaorigen());
        transacciones.setCuentadestino(transaccionesDTO.getCuentadestino());
        return transacciones;
    }

}
