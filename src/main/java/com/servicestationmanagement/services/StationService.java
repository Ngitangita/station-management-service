package com.servicestationmanagement.services;

import com.servicestationmanagement.entities.Station;

import java.util.List;

public interface StationService {
       Station updateStation(Integer id, Station station);

    Station createStation(Station station);

    Station findStationById(Integer stationId);

    Station destroyStationById(Integer id);

    List<Station> findAll();
}
