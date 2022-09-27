package com.etec.tcc.sprint_quiz.configuration;

import com.etec.tcc.sprint_quiz.model.*;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//classe para povoar o banco h2
@Configuration
@Profile("Local")
public class LocalConfigBd {

	
	 @Autowired
	    private AlternativaRepository alternativaRepository;

	    @Autowired
	    private QuestaoRepository questaoRepository;

	    @Autowired
	    private QuestaoService questaoService;
	
	
    @Bean
    public void startDb() {

        //Long id, String nome, String usuario, String senha, String foto, String tipo, List<Questao> questoes, List<Prova> provas
        List<Questao> lq = new ArrayList<>();
        List<Prova> lp = new ArrayList<>();
        Usuario usuario1 = new Usuario(
        		null, "Jonathan", "jonathan@email.com", "12345678", "", "", new ArrayList<Questao>() , new ArrayList<Prova>());

        //Long id, String titulo, String descricao, List<Prova> provas

        CategoriaProva cp = new CategoriaProva(null, "Categoria Prova teste", "Descrição categoriaProva", Arrays.asList(new Prova()));
        CategoriaProva cp1 = new CategoriaProva(null, "Categoria Prova teste 2", "Descrição categoriaProva 2", Arrays.asList(new Prova()));

        //Long id, String nome, String descricao, Integer duracao, Usuario usuario, List<QuestaoProva> questoes, String instituicao, CategoriaProva categoria
        Prova prova1 = new Prova(null, "Prova teste", "Prova descrição", 1, usuario1, null, "instituição teste", cp);

        CategoriaQuestao cq = new CategoriaQuestao(null, "Categoria questao teste", "Descrição categoria questao", Arrays.asList(new Questao()));
        CategoriaQuestao cq1 = new CategoriaQuestao(null, "Categoria questao teste 2", "Descrição categoria questao 2", Arrays.asList(new Questao()));

        Questao questao1 = new Questao(null, "Questao teste", LocalDate.now(), "", "Texto questão teste", null, new Alternativa(), cq1, usuario1);
        Questao questao2 = new Questao(null, "questao2 teste", LocalDate.now(), "", "Texto questao2 teste", null, new Alternativa(), cq1, usuario1);

        QuestaoProva qp1 = new QuestaoProva(null,questao1, prova1);
        QuestaoProva qp2 = new QuestaoProva(null,questao2, prova1);

        //Long id, String texto, String foto, Questao questao
        Alternativa a1 = new Alternativa(null, "texto alternativa a", "", questao1);
        Alternativa a2 = new Alternativa(null, "texto alternativa b", "", questao1);
        Alternativa a3 = new Alternativa(null, "texto alternativa c", "", questao1);

        questao1.setAlternativas(List.of(a1, a2, a3));
        questao2.setAlternativas(List.of(a1, a2, a3));




    }


}
