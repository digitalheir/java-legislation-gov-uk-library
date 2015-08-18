require 'nokogiri'
require 'open-uri'
require 'uri'
require 'fileutils'

def start
  FileUtils.mkdir_p('./files/schema')
  Dir.chdir('./files/schema')
  download_files URI.parse('http://www.legislation.gov.uk/schema/legislation.xsd'), URI.parse('legislation.xsd')
end

def download_files(absolute_url, relative_url)
  xml = Nokogiri::XML open(absolute_url)

  if File.exist? relative_url.to_s
    return
  end

  match = relative_url.to_s.match(/(.*\/)[^\/]*$/)
  if match
    puts "#{absolute_url} and #{relative_url}: changing dir to #{match[1]}"
    FileUtils.mkdir_p(match[1])
    File.open(relative_url.to_s, 'w+') do |f|
      f.puts xml.to_s
    end
    Dir.chdir(match[1])
  else
    File.open(relative_url.to_s, 'w+') do |f|
      f.puts xml.to_s
    end
  end

  xml.root.elements.each do |el|
    pwd = Dir.pwd
    if el.name == 'include'
      relative_url = URI.parse el['schemaLocation']
      abs = absolute_url.merge(relative_url)
      puts abs
      download_files abs, relative_url
    elsif el.name == 'import'
      relative_url = URI.parse el['schemaLocation']
      abs = absolute_url.merge(relative_url)
      puts abs
      download_files abs, relative_url
    end
    Dir.chdir(pwd)
  end
end

start
