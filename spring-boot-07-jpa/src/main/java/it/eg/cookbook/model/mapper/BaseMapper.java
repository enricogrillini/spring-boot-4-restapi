package it.eg.cookbook.model.mapper;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public interface BaseMapper {

    ZoneId LOCAL_ZONE_ID = ZoneId.of("Europe/Rome");

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime value) {
        return value != null
                ? value.atZone(LOCAL_ZONE_ID).toOffsetDateTime()
                : null;
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime value) {
        return value != null
                ? value.atZoneSameInstant(LOCAL_ZONE_ID).toLocalDateTime()
                : null;
    }

}
