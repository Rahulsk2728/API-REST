package com.pojo.rest.workspace;

public class WorkspaceRoot {

  public WorkspaceRoot(Workspace workspace) {
	  this.workspace = workspace;
  }	
	
  public Workspace getWorkspace() {
	  return workspace;
  } 
  
  public void setWorkspace(Workspace workspace) {
	  this.workspace = workspace;
  }
  
  Workspace workspace;
  }
