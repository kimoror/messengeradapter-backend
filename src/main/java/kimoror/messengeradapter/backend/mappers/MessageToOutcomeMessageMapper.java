package kimoror.messengeradapter.backend.mappers;

import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.dto.OutcomeToMessengerMessageDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class MessageToOutcomeMessageMapper {

  abstract OutcomeToMessengerMessageDto convert(MessageDto message);

  public OutcomeToMessengerMessageDto convert(MessageDto message, String botName, String toChatId) {
    OutcomeToMessengerMessageDto outcomeToMessengerMessageDto = convert(message);
    outcomeToMessengerMessageDto.setBotName(botName);
    outcomeToMessengerMessageDto.setToChatId(toChatId);
    return outcomeToMessengerMessageDto;
  }
}
