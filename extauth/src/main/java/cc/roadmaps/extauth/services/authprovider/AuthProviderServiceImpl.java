package cc.roadmaps.extauth.services.authprovider;

import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.model.AuthProviderRepository;
import cc.roadmaps.extauth.services.authprovider.events.AuthProviderSavingEvent;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthProviderServiceImpl implements AuthProviderService {

    private final AuthProviderRepository authProviderRepository;

    @Override
    @Transactional
    public AuthProvider createOrUpdate(AuthProviderSavingEvent command) {
        Optional<AuthProvider> authProviderOptional = authProviderRepository.findByUserIdAndType(command.getUserId(), command.getType());
        AuthProvider authProvider;
        if(authProviderOptional.isPresent()) {
            authProvider = authProviderOptional.get();
            authProvider.updateExternal(command);
        } else {
            authProvider = AuthProvider.create(
                    command.getUserId(),
                    command.getType(),
                    command.getExternalId(),
                    command.getExternalAccessToken(),
                    command.getExternalRefreshToken()
            );
        }
        authProviderRepository.save(authProvider);
        return authProvider;
    }
}
