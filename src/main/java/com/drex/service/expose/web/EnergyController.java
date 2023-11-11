package com.drex.service.expose.web;

import com.drex.service.energy.business.EnergyService;
import com.drex.service.expose.dto.request.EnergyRequest;
import com.drex.service.expose.dto.response.EnergyResponse;
import com.drex.service.expose.dto.response.GraphicResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
@RequestMapping(value = "/energy")
public class EnergyController {

    private final EnergyService energyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{operation}")
    public Completable record(
            @Valid @NotNull @Positive @PathVariable("operation") Long operationId,
            @RequestBody EnergyRequest energyRequest) {
        return energyService.save(operationId, energyRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{operation}/batch")
    public Completable batchRecord(
            @Valid @NotNull @Positive @PathVariable("operation") Long operationId,
            @RequestBody List<EnergyRequest> requests) {
        return energyService.saveAll(operationId, requests);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{operation}")
    public Flowable<EnergyResponse> list(@Valid @NotNull @Positive @PathVariable("operation") Long operationId) {
        return energyService.list(operationId);
    };

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{operation}/last")
    public Single<EnergyResponse> lastRecord(@Valid @NotNull @PathVariable("operation") Long operationId) {
        return energyService.getLastRecord(operationId);
    };


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{operation}/graphic")
    public Flowable<?> getGraphicByOperationId(
            @Valid @NotNull @PathVariable("operation") Long operationId,
            @Valid @NotNull @Param("type") String type,
            @Valid @NotNull @Param("time") String time) {
        return energyService.generateGraphicsByOperationId(operationId, type, time);
    };

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/status")
    public String checkStatus() {
        return "ok";
    }
}
