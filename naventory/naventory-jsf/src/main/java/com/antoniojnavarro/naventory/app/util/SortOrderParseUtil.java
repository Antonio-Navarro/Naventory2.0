package com.antoniojnavarro.naventory.app.util;

import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;

public class SortOrderParseUtil {

	public static SortOrderEnum parseSortOrderPrimefacesToSortOrderDao(SortOrder order) {
		if (order == null) {
			return null;
		}	
		switch (order) {
		case ASCENDING:
			return SortOrderEnum.ASC;
		case DESCENDING:
			return SortOrderEnum.DESC;
		default:
			return SortOrderEnum.ASC;
		}
	}
}
