package org.pilipchuk.diblom.dto.jtable;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class JTableRequest {
    public int jtStartIndex;
    public int jtPageSize;
    public String jtSorting;

    public int getPage() {
        return (int) Math.floor(jtStartIndex / jtPageSize);
    }

    public String getFieldName() {
        return (jtSorting != null) ?
                jtSorting.split(" ")[0] : "";
    }

    public String getSortDirection() {
        return (jtSorting != null && jtSorting.split(" ").length > 1) ?
                jtSorting.split(" ")[1] : "";
    }

    public PageRequest getPageRequest() {
        return (jtSorting == null)
                ? PageRequest.of(getPage(), getJtPageSize())
                : PageRequest.of(getPage(), getJtPageSize(), Sort.Direction.valueOf(getSortDirection()), getFieldName());
    }
}
