package com.drex.service.energy.business.impl;

import com.drex.service.energy.model.entity.Energy;
import com.drex.service.energy.model.entity.Graphic;
import com.drex.service.expose.dto.response.EnergyResponse;
import com.drex.service.expose.dto.request.EnergyRequest;
import com.drex.service.expose.dto.response.GraphicResponse;
import com.drex.service.energy.business.EnergyService;
import com.drex.service.energy.mapper.EnergyMapper;
import com.drex.service.energy.mapper.GraphicMapper;
import com.drex.service.energy.repository.EnergyRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyServiceImpl implements EnergyService {

    private final EnergyRepository energyRepository;

    private final EnergyMapper mapper;

    private final GraphicMapper graphicMapper;

    @Override
    public Completable save(Long operation_id, EnergyRequest request) {
        return energyRepository.save(mapper.toEntity(request, operation_id)).ignoreElement();

        /*return deviceService.findByLabel(deviceLabel)
                .flatMapCompletable(device ->
                        repository.save(mapper.toEntity(request, device.getId())).ignoreElement());*/
    }

    @Override
    public Completable saveAll(Long operationId, List<EnergyRequest> requests) {
        return energyRepository.saveAll(mapper.toEntityList(requests, operationId))
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                })
                .ignoreElements();
        /*
        return deviceService.findByLabel(deviceLabel)
                .flatMapCompletable(device ->
                        repository.saveAll(mapper.toEntityList(requests, device.getId())).ignoreElements());

         */
    }

    @Override
    public Flowable<EnergyResponse> list(Long operation_id) {
        return energyRepository.findByOperationId(operation_id)
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró la planta de energía, revise su request");
                }))
                .map(mapper::toResponse);

        /*return deviceService.findByLabel(deviceCode)
                .toFlowable()
                .flatMap(device ->
                        energyRepository.findByDeviceId(device.getId())
                                .switchIfEmpty(Flowable.defer(() -> {
                                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró la planta de energía, revise su request");
                                }))
                        .map(mapper::toResponse));*/

    }

    @Override
    public Single<EnergyResponse> getLastRecord(Long operationId) {
        return energyRepository.getLastRecord(operationId)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay registro de lectura");
                }))
                .map(mapper::toResponse);
        /*
        return deviceService.findByLabel(deviceLabel)
                .flatMap(device -> energyRepository.getLastRecord(device.getId())
                        .switchIfEmpty(Single.defer(() -> {
                            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay registro de lectura");
                        }))
                        .map(mapper::toResponse));
         */

    }

    @Override
    public Flowable<GraphicResponse> generateGraphicsByOperationId(Long operationId, String type, String time) {
        return Flowable.just(operationId)
                .flatMap(id -> {
                    Flowable<Graphic> flowable;
                    switch (type) {
                        case "accumulated-energy":
                            flowable = energyRepository.generateGraphicAccumulatedEnergyKwhByOperationId(id, time);
                            break;
                        case "active-power":
                            flowable = energyRepository.generateGraphicActivePowerByOperationId(id, time);
                            break;
                        case "three-currents":
                            flowable = energyRepository.generateGraphicThreeCurrentsByOperationId(id, time);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid type: " + type);
                    }
                    return handleNoContentAndMapToResponse(flowable);
                });
    }



    private  Flowable<GraphicResponse> handleNoContentAndMapToResponse(Flowable<Graphic> flowable) {
        return flowable
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no record with the operation number");
                }))
                .map(graphicMapper::toResponse);
    }




}
