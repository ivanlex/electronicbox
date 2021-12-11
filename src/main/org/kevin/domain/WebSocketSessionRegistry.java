package org.kevin.domain;

import java.util.*;
import java.util.concurrent.*;

import static org.kevin.common.Commons.EMPTY_STRING;
import static org.kevin.common.Commons.WS_EXPIRED_MINUTE;

public class WebSocketSessionRegistry {

    private final ConcurrentSkipListMap<String /* userId*/, String/* wsSessionId*/> userSessionIds = new ConcurrentSkipListMap<>();
    private final ConcurrentSkipListMap<Date /* userIdValidDate*/, HashSet<String> /* userIds*/> validDates = new ConcurrentSkipListMap<>();
    private final ConcurrentSkipListMap<String /* userId*/, Date/* tokenValidDate*/> userValidDate = new ConcurrentSkipListMap<>();
    private ExecutorService threadPool = Executors.newCachedThreadPool();


    public WebSocketSessionRegistry()
    {
        try
        {
            Runnable removeSessionRecords = () ->{
                while(true){
                    try
                    {
                        removeExpiredUsers();
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };

            Future future = threadPool.submit(removeSessionRecords);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isUserNotExpired(String userId){
        return userSessionIds.containsKey(userId);
    }

    public Date getUserExpiredDate(String userId){
        if (userSessionIds.containsKey(userId))
        {
            return userValidDate.get(userId);
        }
        else
        {
            return null;
        }
    }

    public String getSessionId(String userId){
        if(userSessionIds.containsKey(userId))
        {
            return userSessionIds.get(userId);
        }
        else
        {
            return EMPTY_STRING;
        }
    }

    public void registerSessionId(String userId, String sessionId)
    {
        Date now = new Date();

        if ( !userSessionIds.containsKey(userId))
        {
            userSessionIds.put(userId ,sessionId);
            if( validDates.containsKey(now) )
            {
                validDates.get(now).add(userId);
            }
            else
            {
                HashSet<String> tokens = new HashSet<>();
                tokens.add(sessionId);
                validDates.put(now,tokens);
            }
        }
        else
        {
            renewUserValidDate(userId, now);
        }
    }

    public void unregisterSessionId(String userId, String sessionId)
    {
        throw new UnsupportedOperationException();
    }

    private void renewUserValidDate(String userId, Date loginDate)
    {
        Date lastLoginDate = userValidDate.get(userId);

        if (validDates.containsKey(lastLoginDate))
        {
            Set<String> dateWithSets  = validDates.get(lastLoginDate);

            if (dateWithSets.contains(userId))
            {
                dateWithSets.remove(userId);
            }
        }

        if (validDates.containsKey(loginDate))
        {
            validDates.get(loginDate).add(userId);
        }
        else
        {
            HashSet<String> table = new HashSet<>();
            table.add(userId);
            validDates.put(loginDate,table);
        }
    }


    private void removeExpiredUsers()
    {
        Calendar expiredTime = Calendar.getInstance();
        Date currentTime = new Date();

        NavigableSet navigableSet = validDates.keySet();
        Iterator itr = navigableSet.iterator();

        while (itr.hasNext())
        {
            Date date = (Date)itr.next();
            expiredTime.setTime(date);
            expiredTime.add(Calendar.MINUTE,WS_EXPIRED_MINUTE);
            if (currentTime.compareTo(expiredTime.getTime()) >= 0)
            {
                HashSet<String> entries = validDates.get(date);

                if(entries != null)
                {
                    for(String entry : entries){
                        userSessionIds.remove(entry);  //remove the unavailable session in  userSessionIds
                        userValidDate.remove(entry);
                    }
                }

                validDates.remove(date); //remove the unavailable session in  userLoginDates
            }
            else
            {
                break;
            }
        }

    }
}
