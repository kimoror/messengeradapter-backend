package kimoror.messengeradapter.backend.mappers;

import java.io.IOException;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface  MessageMapper {

  @Mapping(target = "text", source = "text")
  @Mapping(target = "media", source = "media")
  @Mapping(target = "filename", source = "filename")
  @Mapping(target = "botId", source = "botId")
  @Mapping(target = "toPhoneNumber",source = "toPhoneNumber")
  public Message convert(MessageDto dto) throws IOException;
}
