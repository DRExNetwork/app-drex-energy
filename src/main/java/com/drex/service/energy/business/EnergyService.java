package com.drex.service.energy.business;

import com.drex.service.expose.dto.response.EnergyResponse;
import com.drex.service.expose.dto.request.EnergyRequest;
import com.drex.service.expose.dto.response.GraphicResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface EnergyService {

    Completable save(Long operation_id, EnergyRequest energyRequest);

    Completable saveAll(Long operation_id, List<EnergyRequest> energyRequest);

    Flowable<EnergyResponse> list(Long operation_id);

    Single<EnergyResponse> getLastRecord(Long operationId);

    //Flowable<GraphicResponse> getGraphicByDeviceCode(Long operationId, String type, String time);

    Flowable<GraphicResponse> generateGraphicsByOperationId(Long operationId, String type, String time);


}
