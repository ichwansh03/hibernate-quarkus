package com.ichwan.students;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @GET
    public Response getAllStudent(){
        List<Student> students = studentRepository.listAll();
        return Response.ok(students).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return studentRepository.findByIdOptional(id)
                .map(student -> Response.ok(student).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("name/{name}")
    public Response getByName(@PathParam("name") String name) {
        return studentRepository.find("name", name)
                .singleResultOptional()
                .map(student -> Response.ok(student).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("gpa/{gpa}")
    public Response getByGpa(@PathParam("gpa") Double gpa) {
        List<Student> students = studentRepository.cumlaudeStudents(gpa);
        return Response.ok(students).build();
    }

    @POST
    @Transactional
    public Response create(Student student) {
        studentRepository.persist(student);
        if (studentRepository.isPersistent(student)) {
            return Response.created(URI.create("/students/"+student.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(Student updateStudent, @PathParam("id") Long id){

        Student student = studentRepository.findById(id);
        student.setName(updateStudent.getName());
        student.setNim(updateStudent.getNim());
        student.setGpa(updateStudent.getGpa());
        studentRepository.persist(student);

        return Response.accepted(student).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean delete = studentRepository.deleteById(id);
        return delete ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
