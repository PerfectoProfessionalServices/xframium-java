package org.xframium.page.keyWord.gherkinExtension;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;
import org.xframium.page.PageManager;
import org.xframium.page.data.DefaultPageData;
import org.xframium.page.data.PageData;
import org.xframium.page.data.provider.AbstractPageDataProvider;
import org.xframium.page.data.provider.PageDataProvider;
import org.xframium.page.keyWord.*;
import org.xframium.page.keyWord.KeyWordParameter.ParameterType;
import org.xframium.page.keyWord.KeyWordStep.StepFailure;
import org.xframium.page.keyWord.step.KeyWordStepFactory;

import java.util.ArrayList;
import java.util.List;

public class XMLFormatter extends AbstractPageDataProvider implements Formatter
{
    private List<KeyWordStep> backgroundSteps = new ArrayList<KeyWordStep>( 10 );
    private List<KeyWordTest> scenarioList = new ArrayList<KeyWordTest>( 10 );
    private KeyWordTest currentScenario;
    private PageDataProvider pageDataProvider;
    private Section currentSection = Section.FEATURE;
    
    private enum Section
    {
        FEATURE,
        BACKGROUND,
        SCENARIOS,
        OUTLINE,
    }
    
    public XMLFormatter( PageDataProvider pageDataProvider )
    {
        this.pageDataProvider = pageDataProvider;
    }
    
    @Override
    public void background( Background arg0 )
    {
        currentSection = Section.BACKGROUND;
    }

    @Override
    public void close()
    {
        System.out.println( "Close" );
    }

    @Override
    public void done()
    {
        System.out.println( "Done" );

    }

    @Override
    public void endOfScenarioLifeCycle( Scenario arg0 )
    {
        System.out.println("end life" );

    }

    @Override
    public void eof()
    {
        for ( KeyWordTest xTest : scenarioList )
            KeyWordDriver.instance().addTest( xTest );
        
        KeyWordDriver.instance().addPage( PageManager.instance().getSiteName(), "bdd", KeyWordPage.class );

    }

    @Override
    public void examples( Examples arg0 )
    {
        addRecordType( currentScenario.getName(), false );
        String[] columnNames = null;
        
        
        for ( ExamplesTableRow e : arg0.getRows() )
        {
            int namePosition = -1;
            if ( columnNames == null )
            {
                
                columnNames = new String[ e.getCells().size() ];
                for ( int i=0; i<e.getCells().size(); i++ )
                {
                    columnNames[ i ] = e.getCells().get( i );
                    if ( columnNames[ i ].equals( "name" ) )
                        namePosition = i;
                }
            }
            else
            {
                DefaultPageData pageData = new DefaultPageData( currentScenario.getName(), namePosition == -1 ? "" : e.getCells().get( namePosition ), true );
                for ( int i=0; i<e.getCells().size(); i++ )
                {
                    pageData.addValue( columnNames[ i ], e.getCells().get( i ) );
                }
                addRecord( pageData );
                
                scenarioList.add( currentScenario.copyTest( currentScenario.getName() + "!" + pageData.getName() ) );
            }
        }
    }

    @Override
    public void feature( Feature arg0 )
    {

    }

    @Override
    public void scenario( Scenario xTest )
    {
        currentSection = Section.SCENARIOS;
        String tagNames = null;
        if ( xTest.getTags() != null && !xTest.getTags().isEmpty() )
        {
            tagNames = "";
            for ( Tag tagName : xTest.getTags() )
                tagNames = tagNames + tagName.getName() + ",";
            
            tagNames = tagNames.substring( 0, tagNames.length() - 1 );
        }
        
        this.currentScenario = new KeyWordTest( xTest.getName(), true, null, null, false, null, null, 0, xTest.getDescription(), tagNames, null, null );

        for ( KeyWordStep xStep : backgroundSteps )
            this.currentScenario.addStep( xStep );
        scenarioList.add( currentScenario );
    }
    
    @Override
    public void scenarioOutline( ScenarioOutline xTest )
    {
        
        currentSection = Section.OUTLINE;
        String tagNames = null;
        if ( xTest.getTags() != null && !xTest.getTags().isEmpty() )
        {
            tagNames = "";
            for ( Tag tagName : xTest.getTags() )
                tagNames = tagNames + tagName.getName() + ",";
            
            tagNames = tagNames.substring( 0, tagNames.length() - 1 );
        }
        
        this.currentScenario = new KeyWordTest( xTest.getName(), true, null, xTest.getName(), false, null, null, 0, xTest.getDescription(), tagNames, null, null );

        for ( KeyWordStep xStep : backgroundSteps )
            this.currentScenario.addStep( xStep );

    }

    @Override
    public void startOfScenarioLifeCycle( Scenario arg0 )
    {
        
        System.out.println("start life" );

    }

    @Override
    public void step( Step xStep )
    {
        
        KeyWordStep step = KeyWordStepFactory.instance().createStep( xStep.getName(), "bdd", true, "CALL", "", false, StepFailure.ERROR, false, null, null, null, 0, "", 0, "", null, null, null, null, false, false, null, null );
         
        switch( currentSection )
        {
            case BACKGROUND:
                backgroundSteps.add( step );
                break;
            case FEATURE:
                break;
            case OUTLINE:
                if ( currentScenario.getDataDriver() != null )
                    step.addParameter( new KeyWordParameter( ParameterType.STATIC, "bdd=" + currentScenario.getDataDriver(), null, null ) );
                currentScenario.addStep( step );
                break;
            case SCENARIOS:
                currentScenario.addStep( step );
                break;
        }
    }

    @Override
    public void syntaxError( String arg0, String arg1, List<String> arg2, String arg3, Integer arg4 )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void uri( String arg0 )
    {
        System.out.println( arg0 );

    }

    
    
    @Override
    public void readPageData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public PageData getRecord( String recordType )
    {
        PageData pageData = null;
        if ( pageDataProvider != null )
        {
           pageData = pageDataProvider.getRecord( recordType );
           if ( pageData != null )
               return pageData;
        }
        
        return super.getRecord( recordType );
    }
    
    @Override
    public PageData getRecord( String recordType, String recordId )
    {
        PageData pageData = null;
        if ( pageDataProvider != null )
        {
           pageData = pageDataProvider.getRecord( recordType, recordId );
           if ( pageData != null )
               return pageData;
        }
        
        return super.getRecord( recordType, recordId );
    }
    
    @Override
    public PageData[] getRecords( String recordType )
    {
        PageData[] pageData = null;
        if ( pageDataProvider != null )
        {
           pageData = pageDataProvider.getRecords( recordType );
           if ( pageData != null )
               return pageData;
        }
        
        return super.getRecords( recordType );
    }

}
