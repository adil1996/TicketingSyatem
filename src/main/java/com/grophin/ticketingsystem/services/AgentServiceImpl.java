package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.AssignRequest;
import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.response.AssignResponse;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;
import com.grophin.ticketingsystem.models.AssignedAgent;
import com.grophin.ticketingsystem.repos.AgentsDetailsRepo;
import com.grophin.ticketingsystem.repos.AssignedAgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class AgentServiceImpl implements AgentServiceInterface {

    AgentsDetailsRepo agentsDetailsRepo;
    AssignedAgentRepo assignedAgentRepo;

    @Autowired
    public AgentServiceImpl(AgentsDetailsRepo agentsDetailsRepo, AssignedAgentRepo assignedAgentRepo){
        this.agentsDetailsRepo = agentsDetailsRepo;
        this.assignedAgentRepo = assignedAgentRepo;
    }

    @Override
    public CreateResponse addUserAgent(CreateRequest createRequest) throws Exception {
        CreateResponse agentCreateResponse = new CreateResponse();
        try{
            AgentDetails agentDetails = new AgentDetails();
            agentDetails.setAgentId(createRequest.getId());
            agentDetails.setAgentName(createRequest.getName());
            agentDetails.setAgentEmail(createRequest.getEmail());
            agentDetails.setAgentContactNo(createRequest.getContactNo());
            agentDetails.setAddress(createRequest.getAddress());
            agentDetails.setPassword(createRequest.getPassword());
            this.agentsDetailsRepo.save(agentDetails);

            agentCreateResponse.setId(createRequest.getId());
            agentCreateResponse.setName(createRequest.getName());
            agentCreateResponse.setStatus(1);
            agentCreateResponse.setMessage("Agent Added Successfully");

        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            agentCreateResponse.setStatus(0);
            agentCreateResponse.setMessage("Exception while adding\n"+stringWriter);
        }

        return agentCreateResponse;

    }

    @Override
    public AssignResponse assignAgent(AssignRequest assignRequest) throws Exception {
        AssignResponse assignResponse = new AssignResponse();
        try{
            AssignedAgent assignedAgent = new AssignedAgent();
            assignedAgent.setAgentId(assignRequest.getAgentId());
            assignedAgent.setTicketId(assignRequest.getTicketId());
            this.assignedAgentRepo.save(assignedAgent);

            assignResponse.setAgentId(assignRequest.getAgentId());
            assignResponse.setTicketId(assignRequest.getTicketId());
            assignResponse.setStatus(1);
            assignResponse.setMessage("Successfully assigned");
        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            assignResponse.setStatus(0);
            assignResponse.setMessage("Exception while adding\n"+stringWriter);
        }
        return assignResponse;
    }

    @Override
    public AgentDetails getAgentDetails(String email) throws Exception {
        return this.agentsDetailsRepo.findByAgentEmail(email);
    }
}
