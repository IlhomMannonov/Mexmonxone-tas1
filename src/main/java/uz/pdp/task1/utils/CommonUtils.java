package uz.pdp.task1.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.pdp.task1.exeptions.PageSizeException;

public class CommonUtils {

    public static boolean validatePageAndSize(int page, int size) throws PageSizeException {
        if (page < 0) {
            throw new PageSizeException("Invalid Page number. Page number must not be less than zero!");
        } else if (size < 0) {
            throw new PageSizeException("Invalid Page size. Page size must not be less than zero!");
        } else if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new PageSizeException("Invalid Page size. Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE + "!");
        }
        return true;
    }

    public static Pageable simplePageable(int page, int size) throws PageSizeException {
        validatePageAndSize(page, size);
        return PageRequest.of(page, size);
    }

    public static Pageable descOrAscByCreatedAtPageable(int page, int size, boolean asc) throws PageSizeException {
        validatePageAndSize(page, size);
        if (asc) {
            return PageRequest.of(page, size, Sort.Direction.ASC, "id");
        }
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }

}
