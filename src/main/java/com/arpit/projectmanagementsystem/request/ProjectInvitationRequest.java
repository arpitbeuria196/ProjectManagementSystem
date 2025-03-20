package com.arpit.projectmanagementsystem.request;

import lombok.Data;

@Data
public class ProjectInvitationRequest {
    private Long projectId;
    private String email;
}
