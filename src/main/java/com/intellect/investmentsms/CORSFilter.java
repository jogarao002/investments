package com.intellect.investmentsms;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept,authToken,userid,id,roleId,type, districtId,workFlowId,isActive,categoryId, mandalId, dccbId, pacsId, loginId, relationType, isExceptional,level,previousStepId, status, projectId,roleName,approverId, isMaster,vendorId,categoryName,statusName,apexId,milestoneId,idsList,dccbBrId,isStd,encryptId,columnsList,titleName,subTitleName,membershipId,samplingList, societyCodes, pacsCodes,roleNamesList,list,startDate,endDate,samplingId,calssificationName,membershipCount,misDetailsList,fhrApprovalStatus,tranportCheck,communicationCheck,accomidationCheck,infraCheck,subProductCheck,isManualDeletion,fvrPacsCheck, isManualCreation,subProductId,stateId,dccbId,dccbBrId,asOnDate,isAll,fileName,currentTime,accountNumber,admissionNumber,memberTypeId,memberId,termAccId");
        response.setHeader("Access-Control-Expose-Headers", "Origin, Content-Type, Accept,authToken,userid,id,roleId,type, districtId,workFlowId,isActive,categoryId, mandalId, dccbId, pacsId, loginId, relationType, isExceptional,level,previousStepId, status, projectId,roleName,approverId, isMaster,,vendorId,categoryName,statusName,apexId,milestoneId,idsList,dccbBrId,isStd,encryptId,columnsList,titleName,subTitleName,membershipId,samplingList, societyCodes, pacsCodes,roleNamesList,list,startDate,endDate,samplingId,calssificationName,membershipCount,misDetailsList,fhrApprovalStatus,tranportCheck,communicationCheck,accomidationCheck,infraCheck,subProductCheck,isManualDeletion,fvrPacsCheck, isManualCreation,subProductId,stateId,dccbId,dccbBrId,asOnDate,isAll,fileName,currentTime,accountNumber,admissionNumber,memberTypeId,memberId,termAccId");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept,authToken,userid,id,roleId,type, districtId,workFlowId,isActive,categoryId, mandalId, dccbId, pacsId, loginId, relationType, isExceptional,level,previousStepId, status, projectId,roleName,approverId, isMaster,vendorId,categoryName,statusName,apexId,milestoneId,idsList,dccbBrId,isStd,encryptId,columnsList,titleName,subTitleName,membershipId,samplingList, societyCodes, pacsCodes,roleNamesList,list,startDate,endDate,samplingId,calssificationName,membershipCount,misDetailsList,fhrApprovalStatus,tranportCheck,communicationCheck,accomidationCheck,infraCheck,subProductCheck,isManualDeletion,fvrPacsCheck, isManualCreation,subProductId,stateId,dccbId,dccbBrId,asOnDate,isAll,fileName,currentTime,accountNumber,admissionNumber,memberTypeId,memberId,termAccId");
        response.setHeader("Access-Control-Expose-Headers", "Origin, Content-Type, Accept,authToken,userid,id,roleId,type, districtId,workFlowId,isActive,categoryId, mandalId, dccbId, pacsId, loginId, relationType, isExceptional,level,previousStepId, status, projectId,roleName,approverId, isMaster,vendorId,categoryName,statusName,apexId,milestoneId,idsList,dccbBrId,isStd,encryptId,columnsList,titleName,subTitleName,membershipId,samplingList, societyCodes, pacsCodes,roleNamesList,list,startDate,endDate,samplingId,calssificationName,membershipCount,misDetailsList,fhrApprovalStatus,tranportCheck,communicationCheck,accomidationCheck,infraCheck,subProductCheck,isManualDeletion,fvrPacsCheck, isManualCreation,subProductId,stateId,dccbId,dccbBrId,asOnDate,isAll,fileName,currentTime,accountNumber,admissionNumber,memberTypeId,memberId,termAccId");

        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }
}
