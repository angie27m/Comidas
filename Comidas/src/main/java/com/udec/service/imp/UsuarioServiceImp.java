package com.udec.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udec.entity.Comida;
import com.udec.entity.Usuario;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BusinessLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.RolRepo;
import com.udec.repository.UsuarioRepo;
import com.udec.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService, UserDetailsService{

	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	private RolRepo Rrepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public List<Usuario> get() {
		return repo.findAll();
	}

	@Override
	public Usuario getById(Integer id) {
		Usuario usu = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Este usuario no existe"));
		return usu;
	}

	@Override
	public String save(Usuario entity) {
		String clave = bcrypt.encode(entity.getClave());
		System.out.println("CLAVE------------------" + clave);
		if (entity.getRol()== null || entity.getRol().getIdRol() == null) {
			throw new ArgumentRequiredException("Id rol es requerido");
		} else {
			boolean existencia = Rrepo.existsById(entity.getRol().getIdRol());
			if (existencia) {
				entity.setClave(clave);
				repo.save(entity);
				return "Se ha guardado el usuario";
			} else {
				throw new ArgumentRequiredException("Este rol no existe");
			}
		}
	}

	@Override
	public String update(Usuario entity) {
		if (entity.getIdUsuario() == null) {
			throw new ArgumentRequiredException("Id usuario es requerido");
		}
		Usuario usua = this.getById(entity.getIdUsuario());
		repo.save(usua);
		return "Se ha editado exitosamente";
	}

	@Override
	public void delete(Integer id) {
		boolean existencia = repo.existsById(id);
		if (existencia) {
			repo.deleteById(id);
		} else {
			throw new ModelNotFoundException("Este usuario no existe");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
		Usuario usuario = repo.findByNick(nick);
		if(usuario == null)
			throw new ModelNotFoundException("----Nick o password incorecto");
		if(usuario.isEstado() == false)
			throw new BusinessLogicException("----Usuario deshabilitado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);		
		return ud;
	}

}
