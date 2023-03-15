package br.com.api.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.api.escola.entity.Aluno;
import br.com.api.escola.repository.AlunoRepository;
import br.com.api.escola.service.AlunoService;

@RestController
@RequestMapping("/api")
public class AlunoController {

    private AlunoService alunoWService;

    @PostMapping("/alunos/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String cadastrar(@RequestBody Aluno novoAluno) {
        if(novoAluno == null){
            return String.format("Não existem Informações para novo aluno.", HttpStatus.BAD_REQUEST);
        }else{
            Aluno alunoCad = alunoWService.cadastrar(novoAluno);
            if(alunoCad == null || alunoCad.getId() <= 0){
                return String.format("Não foi possível cadastrar o novo aluno.", HttpStatus.BAD_GATEWAY);
            }
            return String.format("Novo aluno cadastrado.", HttpStatus.CREATED);
        }
    }

    @GetMapping("/alunos/all")
    public List<Aluno> listarAlunos() {
        return alunoWService.listar();
    }

    @GetMapping("/alunos/{id}")
    public Aluno buscarPorId(@PathVariable Long idAluno) {
        var aluno = alunoWService.buscarPorId(idAluno);
        if (aluno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno  não encontrado para o id informado.");
        }
        return aluno;
    }

    @DeleteMapping("/alunos/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirAlunoPorId(@PathVariable Long id) {
       alunoWService.excluirPorId(id);
    }

    @PutMapping("/alunos/{id}")
    public Aluno atualizarAlunoPorId(@PathVariable Long id, @RequestBody Aluno aluno) {
        var alunoAtualizado = alunoWService.atualizarPorId(id, aluno);
        if (alunoAtualizado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }
}
