package com.vaibhav.parkingReservation.customRepositories;

import com.vaibhav.parkingReservation.DTOs.ParkingGarageInfo;
import com.vaibhav.parkingReservation.requests.GarageSearchRequest;
import com.vaibhav.parkingReservation.response.GarageSearchResponse;
import com.vaibhav.parkingReservation.response.Page;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Component
public class GarageCustomRepository {


    @Autowired
    BasicCustomRepository basicCustomRepository;


    private String GARAGE_SELECT_QUERY = "Select distinct new com.vaibhav.parkingReservation.DTOs.ParkingGarageInfo " +
            "( pg.parkingGarageId as parkingGarageId, " +
            "pg.garageName as garageName, " +
            "pg.address.addressLine1 as addressLine1, " +
            "pg.address.addressLine2 as addressLine2, " +
            "pg.address.city as city, " +
            "pg.address.state as state, " +
            "pg.address.country as country, " +
            "pg.address.pinCode as pinCode, " +
            "pg.address.isPersonalAddress as isPersonalAddress, " +
            "pg.isOperational as isOperational, " +
            "pg.user.userId as userId ) ";

    private String DISTINCT_GARAGE_COUNT_QUERY = " select count(pg.parkingGarageId)  ";

    private String GARAGE_FROM_QUERY = " from ParkingGarage pg ";

    public GarageSearchResponse searchGarage(GarageSearchRequest garageSearchRequest, int pageNumber, int pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        final String whereQuery = getWhereQuryForParkingGarageSearch(garageSearchRequest, parameters);
        int totalCount = getTotalCountForSearchRequest(DISTINCT_GARAGE_COUNT_QUERY, parameters, whereQuery, GARAGE_FROM_QUERY);
        if (totalCount == 0) {
            return new GarageSearchResponse(new Page(pageSize, 0, 0), Collections.emptyList());
        }
        List<ParkingGarageInfo> garageInfo = basicCustomRepository.getQueryResult(GARAGE_SELECT_QUERY + GARAGE_FROM_QUERY + whereQuery, parameters, pageNumber, pageSize, ParkingGarageInfo.class);
        GarageSearchResponse garageSearchResponse = new GarageSearchResponse(new Page(pageSize, totalCount, pageNumber), garageInfo);
        return garageSearchResponse;
    }

    private Integer getTotalCountForSearchRequest(final String distinctQuery, final Map<String, Object> parameterMap, final String whereQuery,
                                                  final String fromQuery) {
        StringBuilder sql = new StringBuilder(
                whereQuery.length() + fromQuery.length() + distinctQuery.length() + 1);
        Long queryCount = basicCustomRepository.getQueryCount(
                sql.append(distinctQuery).append(fromQuery).append(whereQuery).toString(),
                parameterMap);
        return queryCount.intValue();
    }

    private String getWhereQuryForParkingGarageSearch(GarageSearchRequest garageSearchRequest, Map<String, Object> parameters) {
        StringBuilder whereQuery = new StringBuilder(" where ");

        if (CommonUtilities.isNotEmpty(garageSearchRequest.getParkingGarageIds())) {
            Set<UUID> garageIds = garageSearchRequest.getParkingGarageIds();
            parameters.put("garageIds", garageIds);
            whereQuery.append(" pg.parkingGarageId in (:garageIds) and ");
        }

        if (CommonUtilities.isNotEmpty(garageSearchRequest.getUserIds())) {
            Set<UUID> userIds = garageSearchRequest.getUserIds();
            parameters.put("garageIds", userIds);
            whereQuery.append(" pg.user.userId in (:userIds) and ");
        }

        if (Objects.nonNull(garageSearchRequest.getGarageName())) {
            String garageName = garageSearchRequest.getGarageName();
            whereQuery.append(" (lower(pg.garageName) like lower('%" + garageName + "%') ) and ");
        }

        if (Objects.nonNull(garageSearchRequest.isOperational())) {
            parameters.put("isOperational", garageSearchRequest.isOperational());
            whereQuery.append(" pg.isOperational = :isOperational and ");
        }

        if (Objects.nonNull(garageSearchRequest.getAddressLine1())) {
            parameters.put("addressLine1", garageSearchRequest.getAddressLine1());
            whereQuery.append(" pg.address.addressLine1 = :addressLine1 and ");
        }

        if (Objects.nonNull(garageSearchRequest.getAddressLine2())) {
            parameters.put("addressLine2", garageSearchRequest.getAddressLine2());
            whereQuery.append(" pg.address.addressLine2 = :addressLine2 and ");
        }

        if (Objects.nonNull(garageSearchRequest.getCity())) {
            parameters.put("city", garageSearchRequest.getCity());
            whereQuery.append(" pg.address.city = :city and ");
        }

        if (Objects.nonNull(garageSearchRequest.getState())) {
            parameters.put("state", garageSearchRequest.getState());
            whereQuery.append(" pg.address.state = :state and ");
        }

        if (Objects.nonNull(garageSearchRequest.getCountry())) {
            parameters.put("country", garageSearchRequest.getCountry());
            whereQuery.append(" pg.address.country = :country and ");
        }

        if (Objects.nonNull(garageSearchRequest.getPinCode())) {
            parameters.put("pinCode", garageSearchRequest.getPinCode());
            whereQuery.append(" pg.address.pinCode = :pinCode and ");
        }
        if (whereQuery.length() < 8) {
            return whereQuery.substring(0, whereQuery.length() - 7);
        }
        return whereQuery.substring(0, whereQuery.length() - 4);
    }
}
