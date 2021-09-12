package com.vaibhav.parkingReservation.customRepositories;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.DTOs.SlotSearchRequest;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeSearchRequest;
import com.vaibhav.parkingReservation.response.Page;
import com.vaibhav.parkingReservation.response.SlotSearchResponse;
import com.vaibhav.parkingReservation.response.SlotTypeSearchResponse;
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
import java.util.stream.Collectors;

@Component
public class SlotCustomRepository {

    @Autowired
    BasicCustomRepository basicCustomRepository;

    private String SLOT_SEARCH_QUERY = "Select distinct new com.vaibhav.parkingReservation.DTOs.SlotDTO " +
            "( st.slotId as slotId, " +
            "st.nearestExit as nearestExit, " +
            "st.nearestExitName as nearestExitName, " +
            "st.slotCode as slotCode, " +
            "st.startDate as startDate, " +
            "st.endDate as endDate, " +
            "st.identifier1 as identifier1, " +
            "st.identifier2 as identifier2, " +
            "st.identifier3 as identifier3, " +
            "st.identifier4 as identifier4, " +
            "st.isFunctional as isFunctional, " +
            "st.slotType.slotTypeId as slotTypeId, " +
            "st.parkingGarage.parkingGarageId as parkingGarageId, " +
            "st.isReserved as isReserved ) ";

    private String DISTINCT_SLOT_COUNT_QUERY = " select count(st.slotId)  ";
    private String SLOT_FROM_CLAUSE = "from Slot st  ";

    private String SLOT_TYPE_SEARCH_QUERY = "Select distinct new com.vaibhav.parkingReservation.DTOs.SlotTypeDTO " +
            "( st.slotTypeId as slotTypeId, " +
            "st.length as length, " +
            "st.breadth as breadth, " +
            "st.typeName as typeName, " +
            "st.hourRate as hourRate, " +
            "st.dayRate as dayRate, " +
            "st.monthRate as monthRate, " +
            "st.dimensionUnit as dimensionUnit, " +
            "st.parkingGarage.parkingGarageId as parkingGarageId ) ";

    private String DISTINCT_SLOT_TYPE_COUNT_QUERY = " select count(st.slotTypeId)  ";
    private String SLOT_TYPE_FROM_CLAUSE = "from SlotType st  ";
    private String NOT_DELETED_CLAUSE = " isDeleted = false  and ";

    public SlotSearchResponse getslots(SlotSearchRequest slotSearchRequest, int pageNumber, int pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        final String whereQuery = generateWhereQueryForSlotSearch(slotSearchRequest, parameters);
        int totalCount = getTotalCountForSearchRequest(DISTINCT_SLOT_COUNT_QUERY, parameters, whereQuery, SLOT_FROM_CLAUSE);
        if (totalCount == 0) {
            return new SlotSearchResponse(new Page(pageSize, 0, 0), Collections.emptyList());
        }
        List<SlotDTO> slots = basicCustomRepository.getQueryResult(SLOT_SEARCH_QUERY + SLOT_FROM_CLAUSE + whereQuery, parameters, pageNumber, pageSize, SlotDTO.class);
        SlotSearchResponse slotSearchResponse = new SlotSearchResponse(new Page(pageSize, totalCount, pageNumber), slots);
        return slotSearchResponse;
    }

    public SlotTypeSearchResponse getslotTypes(SlotTypeSearchRequest slotTypeSearchRequest,int pageNumber, int pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        final String whereQuery = generateWhereQueryForSlotTypeSearch(slotTypeSearchRequest, parameters);
        int totalCount = getTotalCountForSearchRequest(DISTINCT_SLOT_TYPE_COUNT_QUERY, parameters, whereQuery, SLOT_TYPE_FROM_CLAUSE);
        if (totalCount == 0) {
            return new SlotTypeSearchResponse(new Page(pageSize, 0, 0), Collections.emptyList());
        }
        List<SlotTypeDTO> slotTypes = basicCustomRepository.getQueryResult(SLOT_TYPE_SEARCH_QUERY + SLOT_TYPE_FROM_CLAUSE + whereQuery, parameters, pageNumber, pageSize, SlotTypeDTO.class);
        SlotTypeSearchResponse slotTypeSearchResponse = new SlotTypeSearchResponse(new Page(pageSize, totalCount, pageNumber), slotTypes);
        return slotTypeSearchResponse;
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

    private String generateWhereQueryForSlotSearch(SlotSearchRequest slotSearchRequest, Map<String, Object> parameters) {
        StringBuilder whereQuery = new StringBuilder(" where ");
        if (CommonUtilities.isNotEmpty(slotSearchRequest.getSlotIds())) {
            Set<UUID> slotIds = slotSearchRequest.getSlotIds().stream().map(slotTypeId -> UUID.fromString(slotTypeId)).collect(Collectors.toSet());
            parameters.put("slotIds", slotIds);
            whereQuery.append(" slotId in (:slotIds) and ");
        }

        if (Objects.nonNull(slotSearchRequest.getIdentifier1())) {
            parameters.put("identifier1", slotSearchRequest.getIdentifier1());
            whereQuery.append(" identifier1 = :identifier1 and ");
        }

        if (Objects.nonNull(slotSearchRequest.getIdentifier2())) {
            parameters.put("identifier2", slotSearchRequest.getIdentifier2());
            whereQuery.append(" identifier2 = :identifier2 and ");
        }

        if (Objects.nonNull(slotSearchRequest.getIdentifier3())) {
            parameters.put("identifier3", slotSearchRequest.getIdentifier3());
            whereQuery.append(" identifier3 = :identifier3 and ");
        }

        if (Objects.nonNull(slotSearchRequest.getIdentifier4())) {
            parameters.put("identifier4", slotSearchRequest.getIdentifier4());
            whereQuery.append(" identifier4 = :identifier4 and ");
        }


        if (Objects.nonNull(slotSearchRequest.getStartDateTime())) {
            parameters.put("startDateTime", slotSearchRequest.getStartDateTime());
            whereQuery.append(" startDateTime >= :startDateTime and ");
        }

        if (Objects.nonNull(slotSearchRequest.getEndDateTime())) {
            parameters.put("endDateTime", slotSearchRequest.getEndDateTime());
            whereQuery.append(" endDateTime <= :endDateTime and ");
        }

        if (Objects.nonNull(slotSearchRequest.getNearestExitDistance())) {
            parameters.put("nearestExitDistance", slotSearchRequest.getNearestExitDistance());
            whereQuery.append(" nearestExitDistance <= :nearestExitDistance and ");
        }

        if (Objects.nonNull(slotSearchRequest.getExitName())) {
            parameters.put("exitName", slotSearchRequest.getExitName());
            whereQuery.append(" exitName = :exitName and ");
        }

        if (Objects.nonNull(slotSearchRequest.getParkingGarageId())) {
            parameters.put("parkingGarageId", UUID.fromString(slotSearchRequest.getParkingGarageId()));
            whereQuery.append(" parkingGarageId = :parkingGarageId and ");
        }
        if (whereQuery.length() < 8) {
            return "where isDeleted = false ";
        }
        return whereQuery.append(NOT_DELETED_CLAUSE).toString();
    }

    private String generateWhereQueryForSlotTypeSearch(SlotTypeSearchRequest slotTypeSearchRequest, Map<String, Object> parameters) {
        StringBuilder whereQuery = new StringBuilder(" where ");
        if (CommonUtilities.isNotEmpty(slotTypeSearchRequest.getSlotTypeIds())) {
            Set<UUID> slotTypeIds = slotTypeSearchRequest.getSlotTypeIds().stream().map(slotTypeId -> UUID.fromString(slotTypeId)).collect(Collectors.toSet());
            parameters.put("slotTypeIds", slotTypeIds);
            whereQuery.append(" slotTypeId in (:slotTypeIds) and ");
        }

        if (Objects.nonNull(slotTypeSearchRequest.getLength())) {
            parameters.put("length", slotTypeSearchRequest.getLength());
            whereQuery.append(" length <= :length and ");
        }


        if (Objects.nonNull(slotTypeSearchRequest.getBreadth())) {
            parameters.put("breadth", slotTypeSearchRequest.getBreadth());
            whereQuery.append(" breadth <= :breadth and ");
        }

        if (Objects.nonNull(slotTypeSearchRequest.getDayRate())) {
            parameters.put("dayRate", slotTypeSearchRequest.getDayRate());
            whereQuery.append(" dateRate <= :dayRate and ");
        }

        if (Objects.nonNull(slotTypeSearchRequest.getMonthRate())) {
            parameters.put("monthRate", slotTypeSearchRequest.getMonthRate());
            whereQuery.append(" monthRate <= :monthRate and ");
        }

        if (Objects.nonNull(slotTypeSearchRequest.getHourRate())) {
            parameters.put("hourRate", slotTypeSearchRequest.getHourRate());
            whereQuery.append(" hourRate <= :hourRate and ");
        }
        if (whereQuery.length() < 8) {
            return "where isDeleted = false ";
        }
        return whereQuery.append(NOT_DELETED_CLAUSE).toString();
    }
}
