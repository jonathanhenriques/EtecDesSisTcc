package com.etec.tcc.sprint_quiz.model.dto;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.etec.tcc.sprint_quiz.model.dto.QuestaoProvaDTO.converterListaParaDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer duracao;
    private Long usuario;
    private String instituicao;
    private Long categoria;
    private List<QuestaoProvaDTO> questoes;

    public ProvaDTO(Prova prova) {
        this.id = prova.getId();
        this.nome = prova.getNome();
        this.descricao = prova.getDescricao();
        this.duracao = prova.getDuracao();
        this.usuario = prova.getUsuario().getId();
//        if(converterListaParaDto(prova.getQuestoes()).isEmpty()){
//            this.questoes = new ArrayList<>();
//        } else {
//            this.questoes = (converterListaParaDto(prova.getQuestoes()));
//        }
        this.instituicao = prova.getInstituicao();
        this.categoria = prova.getCategoria().getId();
    }

    public static Prova converterEmProva(ProvaDTO dto, Usuario usuario, CategoriaProva categoria) {
        Prova p = new Prova();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setDuracao(dto.getDuracao());
        p.setUsuario(usuario);
        p.setInstituicao(dto.getInstituicao());
        p.setCategoria(categoria);

        return p;
    }


}
