package com.ayush.test.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedArgumentException;
import com.ayush.FriendManagement.entity.BlockEntity;
import com.ayush.FriendManagement.exceptions.BlockingAlreadyExistException;
import com.ayush.FriendManagement.repository.BlockRepository;
import com.ayush.FriendManagement.service.BlockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BlockServiceTest {

    @Mock
    private BlockRepository blockDao;

    @InjectMocks
    private BlockService serviceUnderTest;

    @Test(expected = RepeatedArgumentException.class)
    public void testCreateBlocksWithTwoSameNames(){
        String email1 = "abc@example.com";
        String email2 = "ABC@example.com";
        serviceUnderTest.createBlocking(email1, email2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBlocksWithNullEmail(){
        serviceUnderTest.createBlocking(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBlocksWithBlankTargetEmail(){
        serviceUnderTest.createBlocking("abc@example.com", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBlocksWithRequestorEmail(){
        serviceUnderTest.createBlocking("abc@example.com", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBlocksWithTargetEmail(){
        serviceUnderTest.createBlocking(null,"abc@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBlocksWithBlankRequestEmail(){
        serviceUnderTest.createBlocking("","abc@example.com");
    }

    @Test
    public void createBlockingWithValidData(){
        doNothing().when(blockDao).saveBlock(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createBlocking(email1, email2);
        assertThat(response, is(equalTo(true)));
    }

    @Test(expected = BlockingAlreadyExistException.class)
    public void createBlockingWithValidDataAlreadyExisting(){
        doThrow(DataIntegrityViolationException.class).when(blockDao).saveBlock(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createBlocking(email1, email2);
    }

    @Test
    public void testIsRelationShipBlockedWithBlockedRecord(){
        BlockEntity mockedBlockEntity = new BlockEntity();
        String email1 = "abc@example.com";
        String email2 = "xyz@example.com";
        when(blockDao.getBlockEntity(email1, email2)).thenReturn(mockedBlockEntity);
        boolean actual = serviceUnderTest.isRelationShipBlocked(email1, email2);
        assertThat(actual, is(equalTo(true)));

    }

    @Test
    public void testIsRelationShipBlockedWithBlockedRecordFromOtherPerson(){
        BlockEntity mockedBlockEntity = new BlockEntity();
        String email1 = "abc@example.com";
        String email2 = "xyz@example.com";
        when(blockDao.getBlockEntity(email1, email2)).thenReturn(null);
        when(blockDao.getBlockEntity(email2, email1)).thenReturn(mockedBlockEntity);
        boolean actual = serviceUnderTest.isRelationShipBlocked(email1, email2);
        assertThat(actual, is(equalTo(true)));

    }

    @Test
    public void testIsRelationShipBlockedWithNoRecord(){
        BlockEntity mockedBlockEntity = null;
        when(blockDao.getBlockEntity(anyString(), anyString())).thenReturn(mockedBlockEntity);
        boolean actual = serviceUnderTest.isRelationShipBlocked("abc@example.com", "xyz@example.com");
        assertThat(actual, is(equalTo(false)));

    }


}
