package com.vaibhav.parkingReservation.customRepositories;

import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeSearchRequest;
import com.vaibhav.parkingReservation.response.Page;
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

    private String DISTINCT_COUNT_QUERY = " select count(st.slotTypeId)  ";
    private String SLOT_TYPE_FROM_CLAUSE = "from SlotType st  ";

    public SlotTypeSearchResponse getslotTypes(SlotTypeSearchRequest slotTypeSearchRequest,int pageNumber, int pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        final String whereQuery = generateWhereQueryForSlotTypeSearch(slotTypeSearchRequest, parameters);
        int totalCount = getTotalSlotTypesForSearchRequest(parameters, whereQuery, SLOT_TYPE_FROM_CLAUSE);
        if (totalCount == 0) {
            return new SlotTypeSearchResponse(new Page(pageSize, 0, 0), Collections.emptyList());
        }
        List<SlotTypeDTO> slotTypes = basicCustomRepository.getQueryResult(SLOT_TYPE_SEARCH_QUERY + SLOT_TYPE_FROM_CLAUSE + whereQuery, parameters, pageNumber, pageSize, SlotTypeDTO.class);
        SlotTypeSearchResponse slotTypeSearchResponse = new SlotTypeSearchResponse(new Page(pageSize, totalCount, pageNumber), slotTypes);
        return slotTypeSearchResponse;
    }

    private Integer getTotalSlotTypesForSearchRequest(final Map<String, Object> parameterMap, final String whereQuery,
                                                       final String fromQuery) {
        StringBuilder sql = new StringBuilder(
                whereQuery.length() + fromQuery.length() + DISTINCT_COUNT_QUERY.length() + 1);
        Long queryCount = basicCustomRepository.getQueryCount(
                sql.append(DISTINCT_COUNT_QUERY).append(fromQuery).append(whereQuery).toString(),
                parameterMap);
        return queryCount.intValue();
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
            return " ";
        }
        return whereQuery.substring(0, whereQuery.length() - 4);
    }
}
