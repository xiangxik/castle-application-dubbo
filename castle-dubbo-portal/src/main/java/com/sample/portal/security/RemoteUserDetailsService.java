package com.sample.portal.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.castle.security.CustomUserDetails;
import com.sample.api.UserApi;
import com.sample.domain.User;

public class RemoteUserDetailsService implements UserDetailsService {

	@Reference
	private UserApi userApi;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userApi.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}

		String password = userApi.getUserPassword(user.getId());
		return new CurrentUserDetails(user.getId(), user.getUsername(), password, true, true, true, true,
				AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	public class CurrentUserDetails extends CustomUserDetails<Long, User> {

		public CurrentUserDetails(Long id, String username, String password, boolean enabled, boolean accountNonExpired,
				boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities) {
			super(id, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
					authorities);
		}

		private static final long serialVersionUID = 8220061317304759492L;

		@Override
		public User getCustomUser() {
			return userApi.findOne(getId());
		}
	}
}
