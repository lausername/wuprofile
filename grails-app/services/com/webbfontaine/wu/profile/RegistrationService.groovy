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
}
