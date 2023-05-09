package kimoror.messengeradapter.backend.mappers;

import java.io.IOException;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
@Component
public interface MessageMapper {

  @Mapping(target = "text", source = "text")
  @Mapping(target = "media", expression="java(getMedia(dto))")
  @Mapping(target = "fileName", source = "fileName")
  @Mapping(target = "botId", source = "botId")
  @Mapping(target = "toPhoneNumber",source = "toPhoneNumber")
  @Mapping(target = "request_id",source = "requestId")
  Message convert(MessageDto dto) throws IOException;

  default String getMedia(MessageDto dto) {
    if (StringUtils.hasText(dto.getPhotoUrl())) {
      return dto.getPhotoUrl();
    } else if (StringUtils.hasText(dto.getAudioUrl())) {
      return dto.getAudioUrl();
    } else if (StringUtils.hasText(dto.getVideoUrl())) {
      return dto.getVideoUrl();
    } else if (StringUtils.hasText(dto.getDocumentUrl())) {
      return dto.getDocumentUrl();
    }
    return null;
  }
}