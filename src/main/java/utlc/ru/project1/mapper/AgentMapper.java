package utlc.ru.project1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import utlc.ru.project1.database.entity.Agent;
import utlc.ru.project1.dto.agent.AgentReadDto;
import utlc.ru.project1.dto.agent.AgentCreateUpdateDto;

@Mapper
public interface AgentMapper {
        AgentReadDto toDto(Agent agent);  // Entity to DTO

        Agent toEntity(AgentCreateUpdateDto createUpdateDto);  // DTO to Entity

        Agent update(@MappingTarget Agent agent, AgentCreateUpdateDto agentCreateUpdateDto);
}
