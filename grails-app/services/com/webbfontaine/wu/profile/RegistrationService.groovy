package com.webbfontaine.wu.profile

import grails.gorm.transactions.Transactional
import grails.io.IOUtils
import grails.util.Environment
import groovy.transform.CompileStatic

@Transactional
class RegistrationService {

    private final static HashMap MIME_TYPE          =  [jpg: 'image/jpeg', pdf: 'application/pdf',png: 'image/png']
    private final static String  KEY_FILE_EXTENSION = 'fileExtension'
    private final static String  KEY_IMAGE_FILE     = 'imageFile'
    private final static String  KEY_TEMP_ID        = 'tmpId'
    private final static String  KEY_FILE_EXT       = 'fileExtension'

    def register(Organization organization, Owner owner) {

    }

    @SuppressWarnings("GrMethodMayBeStatic")
    def addAttachment(Organization org, Map params, List<String> attTmpList = []) {
        Integer fileCount = (fileCount(params) - 1)
        Closure attCreation = { domain, tmpList, counter = null ->
            String uploadFileName = getUploadFilename(params, counter)
            String uploadFileExtension = getUploadFileExtension(params, counter) as String
            if (uploadFileName && uploadFileExtension) {
                AttachedFile attachment = new AttachedFile(organization: org,  )
                org.addToAttachedFiles(attachment)
                tmpList << attachment?.attTmpId
            }
            return tmpList
        }
        if (fileCount) {
            (0..fileCount).each { counter ->
                attTmpList = attCreation(org, attTmpList, counter)
            }
        }
        else {
            attTmpList = attCreation(org, attTmpList)
        }
        return attTmpList
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    def createTempFileInformation(Map params, List attCacheList = [], List informationContainer = []) {

        Integer fileCount = (fileCount(params) - 1)
        if (attCacheList) {
            (0..fileCount).each { counter ->
                try {
                    def attachedFile = getParamContent(params, KEY_IMAGE_FILE, counter, fileCount)
                    String fileExtension = getParamContent(params, KEY_FILE_EXT, counter, fileCount)
                    String tmpId = params.get(KEY_TEMP_ID).toString()

                    if (attachedFile) {
                        InputStream file = attachedFile?.inputStream
                        String fileName = attachedFile.originalFilename.replace(".${fileExtension}", "")
                        String pathDir = "${System.getProperty("java.io.tmpdir")}/acd/attachedDocs/attachment-${tmpId}"

                        def dir = new File(pathDir)
                        dir.mkdirs()
                        dir.deleteOnExit()
                        File temp
                        if (pathDir && Environment.current != Environment.TEST) {
                            temp = File.createTempFile(fileName, fileExtension, dir)
                        } else {
                            temp = File.createTempFile(fileName, fileExtension)
                        }

                        IOUtils.copy(file, new FileOutputStream(temp))
                        temp.deleteOnExit()
                        informationContainer.add(informationSetter(attCacheList[counter], fileName, fileExtension, temp.path, tmpId))
                    }
                } catch (IOException exception) {
                    log.warn("File not saved, encountered : ${exception}")
                }
            }
        }
        return informationContainer
    }

    private static Integer fileCount(Map params, int count = 1) {
        if (params.get(KEY_IMAGE_FILE) instanceof LinkedList) {
            count = params.get(KEY_IMAGE_FILE)?.size ?: 0
        }
        return count
    }

    private static getParamContent(Map params, String key, Integer index, Integer count = 0, Object returnValue = null) {
        if (count) {
            returnValue = params.get(key)[index]
        } else {
            returnValue = params.get(key)
        }
        return returnValue
    }

    @CompileStatic
    private static LinkedHashMap informationSetter(String attTmpId, String fileName, String fileExtension, def path, String tmpId) {
        return ['fileInformation' : [(attTmpId): [ 'fileName': fileName, 'fileExtension': fileExtension, pathname: path]],
                'tmpId'           : tmpId,
                'documentId'      : attTmpId]
    }


    private static String getUploadFilename(Map param, Integer counter= null, String uploadFileName = null) {
        Object obj = param.get(KEY_IMAGE_FILE)
        if(counter || counter.equals(0)) {
            uploadFileName = obj[counter].filename
        } else {
            uploadFileName = obj?.filename
        }
        return uploadFileName
    }

    private static String getUploadFileExtension(Map param, Integer counter= null, String uploadFileExtension = null) {
        Object obj = param.get(KEY_FILE_EXTENSION)
        if(counter || counter.equals(0)) {
            uploadFileExtension = obj[counter]
        } else {
            uploadFileExtension = obj
        }
        return uploadFileExtension
    }
}
