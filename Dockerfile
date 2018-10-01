FROM atlassian/default-image:latest

RUN apt-get update -qqy

# Get wget to download firefox and xvfb
RUN apt-get install -y wget xvfb

# Set firefox version and installation directory through environment variables.
ENV FIREFOX_VERSION 45.0
ENV FIREFOX_DIR /usr/bin/firefox
ENV FIREFOX_FILENAME $FIREFOX_DIR/firefox.tar.bz2

# Download the firefox of specified version from Mozilla and untar it.
RUN mkdir $FIREFOX_DIR
RUN wget -q --continue --output-document $FIREFOX_FILENAME "https://ftp.mozilla.org/pub/firefox/releases/${FIREFOX_VERSION}/linux-x86_64/en-US/firefox-${FIREFOX_VERSION}.tar.bz2"
RUN tar -xaf "$FIREFOX_FILENAME" --strip-components=1 --directory "$FIREFOX_DIR"

# Prepend firefox dir to PATH
ENV PATH $FIREFOX_DIR:$PATH

ENTRYPOINT ["/bin/bash"]