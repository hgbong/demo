package com.example.demo.token;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationTokenProvider {
	/***
     * HTTP ��û���� ��ū ���
     * @param request HTTP ��û
     * @return ��ū
     */
    String parseTokenString(HttpServletRequest request);
 
    /***
     * ��ū �߱�
     * @param userNo ���� No
     * @return ��ū
     */
    AuthenticationToken issue(Long userNo);
 
    /***
     * ��ū���� userNo ���
     * @param token ��ū
     * @return userNo
     */
    Long getTokenOwnerNo(String token);
 
    /***
     * ��ū ��ȿ�� �˻�
     * @param token ��ū
     * @return ��ȿ����
     */
    boolean validateToken(String token);
}
