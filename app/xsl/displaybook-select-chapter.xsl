<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE xsl:stylesheet  [
  <!ENTITY nbsp   "&#160;">
  ]>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html"/>
  <xsl:param name="chapter"/>
  <!-- listPids format (don't forget space surrounding each number) exemple:
    <xsl:variable name="listPids" select="' 1 2 3 '" /> -->
  <xsl:param name="listPids"/>
  <xsl:template match="/book">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>
          <xsl:value-of select="chap/@n"/>
        </title>
        <style type="text/css">
          body {text-align: right; font-size:100%;}
          p.p1{font-family:"Times New Roman", Times, serif; font-size:1.5em;}
          p.p2{font-family:"Times New Roman", Times, serif;}

          blueFont
          {color:#000066;}
        </style>
      </head>
      <body>
        <span dir="rtl">
          <h1>
            <xsl:value-of select="@n"/>
          </h1>
          <xsl:for-each select="chap[@n=$chapter]">
            <xsl:variable name="name" select="@n"/>
            <a id="{$name}">
              <h2>
                <xsl:value-of select="@n"/>
              </h2>
            </a>
            <xsl:for-each select="p">
              <p class="p1">
                <xsl:value-of select="@n"/>
                <xsl:text>&nbsp;</xsl:text>
                <xsl:value-of select="d" disable-output-escaping="yes"/>
              </p>
              <xsl:for-each select="t">
                <xsl:if
                  test="contains(concat(' ', $listPids, ' '),concat(' ', @i,' '))">

                  <xsl:choose>
                    <xsl:when test="@i = 1">
                      <p class="p2" style="color:#688A08">
                        <B>
                          <xsl:text>אונקלוס</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 2">
                      <p class="p2" style="color:#088A85;">
                        <B>
                          <xsl:text>ת' יהונתן</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 3">
                      <p class="p2" style="color:#29088A">
                        <B>
                          <xsl:text>רש"י</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 28">
                      <p class="p2" style="color:#6A0888">
                        <B>
                          <xsl:text>שפתי חכמים</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 4">
                      <p class="p2" style="color:#61210B">
                        <B>
                          <xsl:text>רמב'ן</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 5">
                      <p class="p2" style="color:#0B0B61">
                        <B>
                          <xsl:text>אבן עזרא</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 6">
                      <p class="p2" style="color:#610B5E">
                        <B>
                          <xsl:text>ספורנו</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 7">
                      <p class="p2" style="color:#0B4C5F">
                        <B>
                          <xsl:text>בעל טורים</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 8">
                      <p class="p2" style="color:#5F4C0B">
                        <B>
                          <xsl:text>אור החיים</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 29">
                      <p class="p2" style="color:#21610B">
                        <B>
                          <xsl:text>כלי יקר</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 10">
                      <p class="p2" style="color:#0B4C5F">
                        <B>
                          <xsl:text>מ' דוד</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 11">
                      <p class="p2" style="color:#610B38">
                        <B>
                          <xsl:text>מ' ציון</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 12">
                      <p class="p2" style="color:#610B0B">
                        <B>
                          <xsl:text>רלב'ג</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 30">
                      <p class="p2" style="color:#610B38">
                        <B>
                          <xsl:text>מלבי'ם ביאור תוכן</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 31">
                      <p class="p2" style="color:#292A0A">
                        <B>
                          <xsl:text>מלבי'ם ביאור המילות</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 9">
                      <p class="p2" style="color:#0B3B2E">
                        <B>
                          <xsl:text>תורה תמימה</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 14">
                      <p class="p2" style="color:#5E610B">
                        <B>
                          <xsl:text>ר' עובדיה מברטנורה</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 15">
                      <p class="p2" style="color:#5F4C0B">
                        <B>
                          <xsl:text>תוספות יום טוב</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 17">
                      <p class="p2" style="color:#0B3B39">
                        <B>
                          <xsl:text>רש'י</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 18">
                      <p class="p2" style="color:#210B61">
                        <B>
                          <xsl:text>תוספות</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 20">
                      <p class="p2" style="color:#380B61">
                        <B>
                          <xsl:text>משנה ברורה</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 21">
                      <p class="p2" style="color:#0B2F3A">
                        <B>
                          <xsl:text>ביאור הלכה</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:when test="@i = 23">
                      <p class="p2" style="color:#380B61">
                        <B>
                          <xsl:text>זוהר מתורגם</xsl:text>
                        </B>
                        <xsl:text>&nbsp;&nbsp;</xsl:text>
                        <xsl:value-of select="node()"
                                      disable-output-escaping="yes"/>
                      </p>
                    </xsl:when>
                    <xsl:otherwise>
                      <B>
                        <xsl:text>פרשן</xsl:text>
                      </B>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:if>
              </xsl:for-each>
            </xsl:for-each>
          </xsl:for-each>
        </span>
      </body>
    </html>
  </xsl:template>


</xsl:stylesheet>
