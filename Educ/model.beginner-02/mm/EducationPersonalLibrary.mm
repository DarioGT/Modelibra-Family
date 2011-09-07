<?xml version="1.0" encoding="UTF-8"?>

<metaDomains>
  <metaDomain oid="1236446570948">
    <code>Education</code>
    <metaModels>
      <metaModel oid="1236446582951">
        <code>PersonalLibrary</code>
        <metaConcepts>
          <metaConcept oid="1236446637730">
            <code>Person</code>
            <entry>true</entry>
            <metaConceptGraphics>
              <metaConceptGraphic oid="1236446637738">
                <x>15</x>
                <y>15</y>
                <width>160</width>
                <height>120</height>
              </metaConceptGraphic>
            </metaConceptGraphics>
            <metaProperties>
              <metaProperty oid="1236446654961">
                <code>firstName</code>
                <metaTypeOid>1236446770310</metaTypeOid>
                <id>true</id>
                <increment>false</increment>
                <value>true</value>
              </metaProperty>
              <metaProperty oid="1236446657857">
                <code>lastName</code>
                <metaTypeOid>1236446770310</metaTypeOid>
                <id>true</id>
                <increment>false</increment>
                <value>true</value>
              </metaProperty>
              <metaProperty oid="1236446672936">
                <code>maxNumberOfBooks</code>
                <metaTypeOid>1236446834264</metaTypeOid>
                <id>false</id>
                <increment>false</increment>
                <value>false</value>
                <init>1</init>
              </metaProperty>
            </metaProperties>
            <metaDestinationNeighbors>
              <metaNeighbor oid="1236447233541">
                <code>books</code>
                <metaDestinationConceptOid>1236446643112</metaDestinationConceptOid>
                <id>false</id>
                <min>0</min>
                <max>N</max>
                <internal>false</internal>
              </metaNeighbor>
            </metaDestinationNeighbors>
          </metaConcept>
          <metaConcept oid="1236446643112">
            <code>Book</code>
            <entry>true</entry>
            <metaConceptGraphics>
              <metaConceptGraphic oid="1236446643113">
                <x>364</x>
                <y>28</y>
                <width>120</width>
                <height>100</height>
              </metaConceptGraphic>
            </metaConceptGraphics>
            <metaProperties>
              <metaProperty oid="1236446990889">
                <code>title</code>
                <metaTypeOid>1236446857599</metaTypeOid>
                <id>true</id>
                <increment>false</increment>
                <value>true</value>
              </metaProperty>
              <metaProperty oid="1236446996253">
                <code>author</code>
                <metaTypeOid>1236446770310</metaTypeOid>
                <id>false</id>
                <increment>false</increment>
                <value>false</value>
              </metaProperty>
            </metaProperties>
            <metaDestinationNeighbors>
              <metaNeighbor oid="1236447233542">
                <code>person</code>
                <metaDestinationConceptOid>1236446637730</metaDestinationConceptOid>
                <id>false</id>
                <min>0</min>
                <max>1</max>
                <internal>false</internal>
              </metaNeighbor>
            </metaDestinationNeighbors>
          </metaConcept>
        </metaConcepts>
      </metaModel>
    </metaModels>
  </metaDomain>
</metaDomains>
