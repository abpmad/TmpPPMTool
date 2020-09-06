package com.adbpmad.ppmtool.services;

import com.adbpmad.ppmtool.domain.Project;
import com.adbpmad.ppmtool.exceptions.ProjectIdException;
import com.adbpmad.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        }catch (Exception e){
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase()+"' already exists.");

        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if( project == null ){
            throw new ProjectIdException("Project Id '" + projectId +"' doesn't exists.");
        }
        return project;
    }

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public void deleteByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if( project == null ){
            throw new ProjectIdException("Project Id '" + projectId +"' doesn't exists.");
        }
        projectRepository.delete(project);
//        return project;
    }
}
