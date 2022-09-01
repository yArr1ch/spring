package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.*;
import com.dataart.intern.logista.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SearchService {

    private final ParcelRepository parcelRepository;
    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;
    private final DepotRepository depotRepository;

    public List<Parcel> findParcelsByCity(Integer id) {
        return parcelRepository.findParcelsByCity(id);
    }

    public List<Client> findClientsByCity(Integer id) {
        return clientRepository.findClientsByCity(id);
    }

    public List<Client> findClientByPhoneNumberLike(String number) {
        return clientRepository.findClientByPhoneNumberLike(number);
    }

    public List<City> findCitiesByName(String name) {
        String wrapped = RepositoryUtil.wrap(name);
        return cityRepository.findCitiesByNameLike(wrapped);
    }

    public List<Depot> findDepotByAddressAndNumberOrCityId(String adr, Integer query) {
        String wrapped = RepositoryUtil.wrap(adr);
        return depotRepository.findByAddressAndNumberOrCityId(wrapped, query, query);
    }

    public List<ClientDTO> findClientsStream(Integer cityId) {
        List<Client> clientList = clientRepository.findAll();
        List<Parcel> parcelList = parcelRepository.findAll();
        List<Depot> depotList = depotRepository.findAll();
        Map<Integer, Integer> parcelMap = parcelList.stream().collect(Collectors.toMap
                (Parcel::getSenderId,  Parcel::getId, (p1, p2) -> p1));

        Set<Depot> depotId = depotList.stream().filter(d -> d.getCityId().equals(cityId)).collect(Collectors.toSet());

        List<Parcel> pList = parcelList.stream().filter(p -> depotId.stream().anyMatch(d -> d.getId()
                        .equals(p.getOriginId()) || d.getId().equals(p.getDestinationId()))).collect(Collectors.toList());

        List<Client> cList = clientList.stream().filter(c -> pList.stream().anyMatch(p -> p.getSenderId()
                        .equals(c.getId()) || p.getReceiverId().equals(c.getId()))).collect(Collectors.toList());

        return cList.stream().map(client -> {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(client.getId());
            clientDTO.setFirstName(client.getFirstName());
            clientDTO.setLastName(client.getLastName());
            clientDTO.setPhoneNumber(client.getPhoneNumber());
            Integer pNum = parcelMap.get(client.getId());
            clientDTO.setParcelNumber(pNum);
            return clientDTO;
        }).collect(Collectors.toList());
    }

    public List<DepotDTO> findDepotsStream(Integer num, String adr) {
        List<Depot> depotList = depotRepository.findAll();
        List<City> cityList = cityRepository.findAll();
        Map<Integer, String> cityMap = cityList.stream().collect(Collectors.toMap(City::getId, City::getName));

        List<Depot> depotL = depotList.stream().filter(d -> d.getNumber().equals(num) ||
                d.getAddress().equals(adr)).collect(Collectors.toList());

        return depotL.stream().map(depot -> {
            DepotDTO depotDTO = new DepotDTO();
            depotDTO.setId(depot.getId());
            depotDTO.setNumber(depot.getNumber());
            depotDTO.setAddress(depot.getAddress());
            String cityName = cityMap.get(depot.getCityId());
            depotDTO.setCity(cityName);
            return depotDTO;
        }).collect(Collectors.toList());
    }
}
