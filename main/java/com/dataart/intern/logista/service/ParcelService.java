package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.*;
import com.dataart.intern.logista.model.Parcel;
import com.dataart.intern.logista.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final DepotService depotService;
    private final ClientService clientService;

    public List<ParcelDTO> findAll() {
        List<Parcel> parcels = parcelRepository.findAll();
        List<DepotDTO> depots = depotService.findAll();
        List<Client> clients = clientService.findAll();
        Map<Integer, Client> clientsMap = clients.stream().collect(Collectors.toMap(Client::getId, c -> c));
        Map<Integer, DepotDTO> depotsDTOMap = depots.stream().collect(Collectors.toMap(DepotDTO::getId, d -> d));
        return parcels.stream().map(parcel -> {
            ParcelDTO parcelDTO = new ParcelDTO();
            parcelDTO.setId(parcel.getId());
            parcelDTO.setDescription(parcel.getDescription());
            parcelDTO.setPrice(parcel.getPrice());
            parcelDTO.setWidth(parcel.getWidth());
            parcelDTO.setHeight(parcel.getHeight());
            parcelDTO.setLength(parcel.getLength());
            parcelDTO.setInsuredPrice(parcel.getInsuredPrice());

            Client sender = clientsMap.get(parcel.getSenderId());
            Client receiver = clientsMap.get(parcel.getReceiverId());
            parcelDTO.setSender(sender);
            parcelDTO.setReceiver(receiver);
            DepotDTO origin = depotsDTOMap.get(parcel.getOriginId());
            DepotDTO destination = depotsDTOMap.get(parcel.getDestinationId());
            parcelDTO.setOrigin(origin);
            parcelDTO.setDestination(destination);
            return parcelDTO;

        }).collect(Collectors.toList());
    }

    public Parcel find(Integer id) {
        return parcelRepository.find(id);
    }

    public ParcelDTO findDTO(Integer id) {
        Parcel parcel = parcelRepository.find(id);
        List<DepotDTO> depot = depotService.findAll();
        List<Client> client = clientService.findAll();
        Map<Integer, Client> clientMap = client.stream().collect(Collectors.toMap(Client::getId, c -> c));
        Map<Integer, DepotDTO> depotDTOMap = depot.stream().collect(Collectors.toMap(DepotDTO::getId, d -> d));

        ParcelDTO parcelDTO = new ParcelDTO();
        parcelDTO.setId(parcel.getId());
        parcelDTO.setDescription(parcel.getDescription());
        parcelDTO.setPrice(parcel.getPrice());
        parcelDTO.setWidth(parcel.getWidth());
        parcelDTO.setHeight(parcel.getHeight());
        parcelDTO.setLength(parcel.getLength());
        parcelDTO.setInsuredPrice(parcel.getInsuredPrice());

        Client sender = clientMap.get(parcel.getSenderId());
        Client receiver = clientMap.get(parcel.getReceiverId());
        parcelDTO.setSender(sender);
        parcelDTO.setReceiver(receiver);
        DepotDTO origin = depotDTOMap.get(parcel.getOriginId());
        DepotDTO destination = depotDTOMap.get(parcel.getDestinationId());
        parcelDTO.setOrigin(origin);
        parcelDTO.setDestination(destination);
        return parcelDTO;
    }

    public int create(Parcel parcel) {
        parcel.setPrice((int) ((parcel.getLength() * parcel.getHeight() * parcel.getWidth()) / 100 * 5 + parcel.getInsuredPrice() * 0.005));
        return parcelRepository.create(parcel);
    }

    public void delete(Integer id) {
        parcelRepository.delete(id);
    }

    public void update(Integer id, Parcel parcel) {
        parcelRepository.update(id, parcel);
    }
}
