package com.frigorifico.mendes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frigorifico.mendes.controller.page.PageWrapper;
import com.frigorifico.mendes.model.Usuario;
import com.frigorifico.mendes.repository.Grupos;
import com.frigorifico.mendes.repository.Usuarios;
import com.frigorifico.mendes.repository.filter.UsuarioFilter;
import com.frigorifico.mendes.service.CadastroUsuarioService;
import com.frigorifico.mendes.service.StatusUsuario;
import com.frigorifico.mendes.service.exception.EmailUsuarioJaCadastradoException;
import com.frigorifico.mendes.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private Grupos grupos;

	@Autowired
	private Usuarios usuarios;

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario, UsuarioFilter usuarioFilter, Pageable pageable) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupos.findAll());		
		mv.addObject("conteudo", usuarios.filtrar(usuarioFilter, pageable));
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, Pageable pageable,
			UsuarioFilter usuarioFilter) {
		if (result.hasErrors()) {
			return novo(usuario, usuarioFilter, pageable);
		}

		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario, usuarioFilter, pageable);
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario,usuarioFilter, pageable);
		}

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuarios");
		mv.addObject("grupos", grupos.findAll());
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		cadastroUsuarioService.alterarStatus(codigos, statusUsuario);
	}
	
	@GetMapping("/novo/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, Pageable pageable,UsuarioFilter usuarioFilter) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario, usuarioFilter, pageable);
		mv.addObject(usuario);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroUsuarioService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
		return "redirect:/usuarios/novo";
	}

}
