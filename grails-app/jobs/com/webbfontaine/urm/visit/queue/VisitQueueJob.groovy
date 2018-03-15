package com.webbfontaine.urm.visit.queue

import com.webbfontaine.urm.rar.operations.SystemOperationHandlerService
import org.slf4j.LoggerFactory


class VisitQueueJob {

    private static final LOGGER = LoggerFactory.getLogger(VisitQueueJob)

    static triggers = {
        cron name: 'visitQueue', cronExpression: "${SystemOperationHandlerService.getVisitQueueCronSched() ?: "0 0/60 * * * ?"}"
    }

    def concurrent = false
    def systemOperationHandlerService

    def execute() {
        if(systemOperationHandlerService.isVisitQueueEnabled()){
            LOGGER.debug("executing job: ${VisitQueueJob}")
            systemOperationHandlerService.doVisitQueueJob()
        }
    }
}
