package ttd.service;

import ttd.model.User;

public interface EmailVerificationService {
    public void scheduleEmailConformation(User user);
}
