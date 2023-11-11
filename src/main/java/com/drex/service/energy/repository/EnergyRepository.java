package com.drex.service.energy.repository;

import com.drex.service.energy.model.entity.Energy;
import com.drex.service.energy.model.entity.Graphic;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface EnergyRepository extends RxJava3CrudRepository<Energy, Long> {

    @Query("SELECT * FROM energy WHERE operation_id = :operationId ORDER BY measurement_time DESC LIMIT 1")
    Maybe<Energy> getLastRecord(Long operationId);

    //Flowable<Energy> findByDeviceId(Long deviceId);

    @Query("SELECT * FROM energy WHERE operation_id = :operationId ORDER BY measurement_time ASC")
    Flowable<Energy> findByOperationId(Long operationId);

    @Query("SELECT DATE_TRUNC(:time, measurement_time) AT TIME ZONE 'UTC' AS measurement_time_utc, MAX(active_power) AS active_power FROM energy WHERE operation_id = :operationId GROUP BY measurement_time_utc ORDER BY measurement_time_utc")
    Flowable<Graphic> generateGraphicActivePowerByOperationId(Long operationId, String time);

    @Query("SELECT DATE_TRUNC(:time, measurement_time) AT TIME ZONE 'UTC' AS measurement_time_utc, MAX(accumulated_energy_kwh) AS accumulated_energy_kwh FROM energy WHERE operation_id = :operationId GROUP BY measurement_time_utc ORDER BY measurement_time_utc")
    Flowable<Graphic> generateGraphicAccumulatedEnergyKwhByOperationId(Long operationId, String time);

    @Query("SELECT DATE_TRUNC(:time, measurement_time) AT TIME ZONE 'UTC' AS measurement_time_utc, MAX(current_a) AS current_a, MAX(current_b) AS current_b, MAX(current_c) AS current_c FROM energy WHERE operation_id = :operationId GROUP BY measurement_time_utc ORDER BY measurement_time_utc")
    Flowable<Graphic> generateGraphicThreeCurrentsByOperationId(Long operationId, String time);
}
