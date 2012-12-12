//
//package utilities;
//import edu.wpi.first.wpilibj.Joystick;
//
///**
// * @author fauzi
// */
//public class PS3Joystick extends Joystick{
//    private Button m_btReleaseBall = new Button();
//    private Button m_btReverseDrive = new Button();
//    private Button m_btActRetrieve = new Button();
//    private Button m_btTurnOnCompressor = new Button();
//    private Button m_btPrintFile = new Button();
//    private Button m_btModifyAuto = new Button();
//    private Button m_btChangeDrive = new Button();
//    private Button m_btAllowEdit = new Button();
//    private Button m_btChangeFile = new Button();
//    private SpecialButton m_btRecord = new SpecialButton();
//    private SpecialButton m_btReplay = new SpecialButton();
//    
//    // Joysticks and buttons
//    private final int btReleaseBall = 1;
//    private final int btReverseDrive = 2;
//    private final int btActRetrieve = 3;
//    private final int btTurnOnCompressor = 4;
//    private final int btPrintFile = 5;
//    private final int btModifyAuto = 6;
//    private final int btChangeDrive = 8;
//    private final int btRecord = 9;
//    private final int btAllowEdit = 10;
//    private final int btChangeFile = 11;
//    private final int btReplay = 12;
//    
//    private boolean m_bAnotherPressed = false;
//    
//    public PS3Joystick(int iPort)
//    {
//        super(iPort);
//    }
//    
//    public void run()
//    {
//        m_btReleaseBall.run(super.getRawButton(btReleaseBall));
//        m_btReverseDrive.run(super.getRawButton(btReverseDrive));
//        m_btActRetrieve .run(super.getRawButton(btActRetrieve));
//        m_btTurnOnCompressor.run(super.getRawButton(btTurnOnCompressor));
//        m_btPrintFile.run(super.getRawButton(btPrintFile));
//        m_btModifyAuto.run(super.getRawButton(btModifyAuto));
//        m_btChangeDrive.run(super.getRawButton(btChangeDrive));
//        m_btAllowEdit.run(super.getRawButton(btAllowEdit));
//        m_btChangeFile.run(super.getRawButton(btChangeFile));
//        m_bAnotherPressed = m_btRecord.run(super.getRawButton(btRecord), m_bAnotherPressed);
//        m_bAnotherPressed = m_btReplay.run(super.getRawButton(btReplay), m_bAnotherPressed);
//    }
//    
//    public boolean btReleaseBall()
//    {
//        return m_btReleaseBall.gotPressed();
//    }
//    
//    public boolean btReverseDrive()
//    {
//        return m_btReverseDrive.gotPressed();
//    }
//    
//    public boolean btActRetrieval()
//    {
//        return m_btActRetrieve.gotPressed();
//    }
//    
//    public boolean btActCompressor()
//    {
//        return m_btTurnOnCompressor.gotPressed();
//    }
//    
//    public boolean btPrintFile()
//    {
//        return m_btPrintFile.gotPressed();
//    }
//    
//    public boolean btModifyAuto()
//    {
//        return m_btModifyAuto.gotPressed();
//    }
//    
//    public boolean btChangeDrive()
//    {
//        return m_btChangeDrive.gotPressed();
//    }
//    
//    public boolean btRecord()
//    {
//        return m_btRecord.getSwitch();
//    }
//    
//    public boolean btAllowEdit()
//    {
//        return m_btAllowEdit.gotPressed();
//    }
//    
//    public boolean btChangeFile()
//    {
//        return m_btChangeFile.gotPressed();
//    }
//    
//    public boolean btReplay()
//    {
//        return m_btReplay.getSwitch();
//    }
//}
