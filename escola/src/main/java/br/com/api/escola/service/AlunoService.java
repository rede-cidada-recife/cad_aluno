package br.com.api.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.api.escola.entity.Aluno;
import br.com.api.escola.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;


    public Aluno cadastrar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        var aluno = alunoRepository.findById(id);
        if (aluno.isEmpty()) {
            return null;
        }
        return aluno.get();
    }

    public void excluirPorId(Long id) {
        var aluno = alunoRepository.findById(id);
        if (aluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cadastro do aluno não encontrado.");
        }
        alunoRepository.delete(aluno.get());
        throw new ResponseStatusException(HttpStatus.OK,"Cadastro do aluno removido.");
    }

    public Aluno atualizarPorId(Long id,Aluno aluno) {
        var alunoAtualizado = alunoRepository.findById(id);
        if (alunoAtualizado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadastro do aluno não encontrado.");
        }
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    
}
