package com.codegym.qltcbe.service.notification;


import com.codegym.qltcbe.model.entity.Notification;
import com.codegym.qltcbe.service.IGeneralService;

import java.util.List;

public interface INotificationService extends IGeneralService<Notification> {
    List<Notification> findAllNotification(Long id);
}
